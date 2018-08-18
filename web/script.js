$(document).ready(function(){
    var timeToWait = 5000 + Math.ceil(Math.random() * 5000);
    console.log(timeToWait);

    setTimeout(function(){
      $('#myModal').modal('show');
    }, timeToWait)
});