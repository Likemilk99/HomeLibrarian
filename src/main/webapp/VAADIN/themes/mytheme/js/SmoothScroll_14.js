/**
 * Created by Александр on 06.04.2016.
 */
function Timer() {

}
Timer.timer;

function smoothScroll(panelID, direction) {

    var time = 800
    var panel = document.getElementById(panelID)

    var h = 400

    if(direction == "left") {
        h = -h
    }

    //var target = document.getElementById(targetID)

    var panelChildren = panel.childNodes;
    var panelSub = panelChildren[1];
    var startScroll = panelSub.scrollLeft;

    // time when scroll starts
    var start = new Date().getTime();
    clearInterval(Timer.timer);
    // set an interval to update scrollTop attribute every 1 ms
    Timer.timer = setInterval(function() {
            // calculate the step, i.e the degree of completion of the smooth scroll
            var step = Math.min(1, (new Date().getTime() - start) / time);
            panelSub.scrollLeft = startScroll + step*h;//(step * target.offsetLeft);
            // document.body['scrollTop'] = (step * target.offsetTop);

            // end interval if the scroll is completed
            if (step == 1) {
                clearInterval(Timer.timer)
            }
    }, 1);
}