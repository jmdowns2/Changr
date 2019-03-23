var imageDiff = require('image-diff');

if(process.argv.length < 5)
{
    console.log("Arguments");
    console.log("nodejs diff.js <file1> <file2> <diffFile>");
    return;
}

var input1 = process.argv[2];
var input2 = process.argv[3];
var diffFile = process.argv[4];


imageDiff({
    actualImage: input1,
    expectedImage: input2,
    diffImage: diffFile,
}, function (err, imagesAreSame) {
    process.exit(imagesAreSame ? 0 : 255);
});