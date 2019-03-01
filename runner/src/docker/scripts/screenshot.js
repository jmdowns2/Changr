var fs = require('fs');
var page = require('webpage').create();
var system = require('system');
var args = system.args;

page.onError = function (msg, trace) {
    console.log(msg);
    trace.forEach(function(item) {
        console.log('  ', item.file, ':', item.line);
    });
};

var url = args[1];
var outputFile = args[2];
page.open(url, function() {
    setTimeout(function(){
//        fs.write('./output.txt', page.content, 'w');
        page.render(outputFile);
        phantom.exit();
    }, 5000);
});

