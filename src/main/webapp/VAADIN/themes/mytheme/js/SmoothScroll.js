/**
 * Created by Александр on 06.04.2016.
 */
function smoothScroll(panelID, targetID) {

    var time = 800
    var panel = document.getElementById(panelID)
    var target = document.getElementById(targetID)

    var panelChildren = panel.childNodes;
    var panelSub = panelChildren[1];
    var startScroll = panelSub.scrollTop;

    // time when scroll starts
    var start = new Date().getTime(),

    // set an interval to update scrollTop attribute every 25 ms
        timer = setInterval(function() {
            // calculate the step, i.e the degree of completion of the smooth scroll
            var step = Math.min(1, (new Date().getTime() - start) / time);

            panelSub.scrollTop = startScroll + (step * target.offsetTop);
            // document.body['scrollTop'] = (step * target.offsetTop);

            // end interval if the scroll is completed
            if (panelSub.scrollTop > target.offsetTop) {
                panelSub.scrollTop = target.offsetTop
                clearInterval(timer);
            }
        }, 25);
}