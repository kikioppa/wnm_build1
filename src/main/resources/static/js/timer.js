setInterval(function time(){
    var d = new Date();
    var hours = 21 - d.getHours();
    var min = 60 - d.getMinutes();
    if((min + '').length == 1){
        min = '0' + min;
    }
    var sec = 60 - d.getSeconds();
    if((sec + '').length == 1){
        sec = '0' + sec;
    }
    jQuery('#the-final-countdown p').html(hours+':'+min+':'+sec)
}, 1000);