document.getElementById('div1').onclick = function(e) {
    var x = e.offsetX==undefined?e.layerX:e.offsetX;
    var y = e.offsetY==undefined?e.layerY:e.offsetY;
    alert(x +'x'+ y);
}