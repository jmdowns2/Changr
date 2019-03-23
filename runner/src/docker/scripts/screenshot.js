var fs = require('fs');
var page = require('webpage').create();
var system = require('system');
var args = system.args;

page.settings.userAgent = 'illa/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3163.100 Safari/537.36';

page.onError = function (msg, trace) {
    console.log(msg);
    trace.forEach(function(item) {
        console.log('  ', item.file, ':', item.line);
    });
};

var url = args[1];
var outputFile = args[2];
var pageWidth = args[3];

if(pageWidth)
{
	var x = pageWidth.split("x")[0];
	var y = pageWidth.split("x")[1];
    page.viewportSize = { height: y, width: x };
}


page.open(url, function() {
    setTimeout(function(){
//        fs.write('./output.txt', page.content, 'w');
        page.render(outputFile);
        phantom.exit();
    }, 5000);
});

