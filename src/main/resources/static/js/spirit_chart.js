

window.onload = function(){

    $.ajax({
        url: "http://localhost:80/price_data/" + spiritCode,
        method: "GET",
        success: function(data) {
            var chartData = [];
            var chartXdata = []
            let prev = 1;
            let prev2 = 80;
            console.log(data);
            console.log("추세데이타");

            for (var i in data) {
                //prev += 5 - Math.random() * 10;
                chartData.push({x:data[i].date,y:data[i].price})
                chartXdata.push(data[i].date)
            }


            var totalDuration = 100;
            var delayBetweenPoints = totalDuration / data.length;
            var previousY = (ctx) => ctx.index === 0 ? ctx.chart.scales.y.getPixelForValue(100) : ctx.chart.getDatasetMeta(ctx.datasetIndex).data[ctx.index - 1].getProps(['y'], true).y;
            var animation = {
                x: {
                    type: 'number',
                    easing: 'linear',
                    duration: delayBetweenPoints,
                    from: NaN, // the point is initially skipped
                    delay(ctx) {
                        if (ctx.type !== 'data' || ctx.xStarted) {
                            return 0;
                        }
                        ctx.xStarted = true;
                        return ctx.index * delayBetweenPoints;
                    }
                },
                y: {
                    type: 'number',
                    easing: 'linear',
                    duration: delayBetweenPoints,
                    from: 100,
                    delay(ctx) {
                        if (ctx.type !== 'data' || ctx.yStarted) {
                            return 0;
                        }
                        ctx.yStarted = true;
                        return ctx.index * delayBetweenPoints;
                    }
                }
            };

            var ctx = document.getElementById('quoteChart');

            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    datasets: [{
                        borderColor: 'red',
                        borderWidth: 1,
                        radius: 0,
                        data: chartData,
                    }]
                },
                options: {
                    animation,
                    interaction: {
                        intersect: false
                    },
                    plugins: {
                        legend: false
                    },
                    scales: {
                        x: {
                            type: 'linear'
                        }
                    }
                }
            })

            $.ajax({
                url: "http://localhost:80/taste_data/"+ spiritId,
                method: "GET",
                success: function(result) {


                    var tastingData = [];
                    var tastingXdata = ['브라이니 (바다향)','플로럴 (꽃향)','오일','바디감 (full)','허브','피트','프루티 (과일향)'
                        ,'리치함','짠맛','스모키','스윗 (sweat)','타르트 (tart)','바닐라'];

                    tastingData.push(result.briny
                        ,result.floral
                        ,result.oily
                        ,result.full
                        ,result.herbal
                        ,result.peat
                        ,result.fruity
                        ,result.rich
                        ,result.salty
                        ,result.smokey
                        ,result.sweat
                        ,result.tart
                        ,result.vanilla

                    )
                    //tastingdata.push(result.floral)
                    /*var tastingData = [];
                    var tastingXdata = [];
                    for (var i in result) {
                        tastingData.push(result.briny)
                        tastingXdata.push(result.floral)
                    }*/


                    var ctx = document.getElementById('tastingChart');

                    var tastingChart = new Chart(ctx, {
                        type: 'polarArea',
                        data: {
                            labels: tastingXdata,
                            datasets: [{
                                data: tastingData,
                                backgroundColor: [
                                    '#3A3335',
                                    '#06D6A0',
                                    '#EF767A',
                                    '#9adbc2',
                                    '#6b5237',
                                    '#fccd7c',
                                    '#FFD700',
                                    '#ff9999',
                                    '#4169E1',
                                    '#7FFFD4',
                                    '#FFDAB9',
                                    '#DC143C',
                                    '#FF7F50',
                                    '#BC8F8F'
                                ],
                                borderColor: [
                                    '#fff'
                                ]
                            }]
                        }

                    })
                }
            })
        }
    })
};