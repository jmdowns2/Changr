//const request = require('request');
const https = require('https');
const AWS = require('aws-sdk');
AWS.config.update({region: 'us-east-1'});
const dynamo = new AWS.DynamoDB({apiVersion: '2012-08-10'});
const s3 = new AWS.S3({apiVersion: '2012-08-10'});

var JOBS_TABLE_NAME = "jobs";
var PROJECTS_TABLE_NAME = "projects";
var OUTPUT_BUCKET = "changrfilerepo";

async function processNewJob(jobId)
{
    console.log("Start process"+jobId);

    var job = await getItem(jobId, JOBS_TABLE_NAME);
    var project = await getItem(job.projectId.S, PROJECTS_TABLE_NAME);
    
    console.log("TYPE = " + project.config.M.type.S);
    if(project.config.M.type.S == "url")
    {
        var url = project.config.M.url.S;
        console.log("URL"+url);
        var data = await fetchUrl(url);
        
        console.log(project);
        var key = `${project.user.S}/${project.id.S}/jobs/${jobId}/out.html`;
        console.log("Write to "+key);
        await uploadToS3(key, data);
        
        job.status = {"S": "FINISHED"};
        job.output = {"S": key};
        await updateItem(job, JOBS_TABLE_NAME)
        console.log("DONE");
    }
    else
    {
        throw new Error("Invalid type");
    }
}

async function fetchUrl(url) 
{
    return new Promise((resolve, reject) => {
        var data = "";
        https.get(url, (res) => {
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', function() {
                resolve(data);
            });
        }).on('error', function(e) {
            reject(e);
        });
    });
}


async function uploadToS3(key, data)
{
    const params = {
        Bucket: OUTPUT_BUCKET,
        Key: key,
        Body: data
    };
    return await s3.upload(params).promise();
    
}
async function getItem(id, table)
{
    console.log("getitem");
    var params = {
        TableName: table,
        Key: {
          'id': {S: id }
        },
      };
      
    var item = await dynamo.getItem(params).promise();
    return item.Item;
}

async function updateItem(item, table)
{
    var params = {
        TableName: table,
        Item: item
      };
    return await dynamo.putItem(params).promise();
}

async function processRecords(records)
{
    for(var i=0; i<records.length; ++i)
    {
        var record = records[i];
        console.log(record.eventID);
        console.log(record.eventName);
        console.log('DynamoDB Record: %j', record.dynamodb);

        if(record.eventName == "INSERT")
        {
           await processNewJob(record.dynamodb.Keys.id.S);
        }
    };
}

exports.dynamoJobChangeHandler =  async (event, context) => {
    await processRecords(event.Records);
}
