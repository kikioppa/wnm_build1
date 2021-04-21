

window.onload = function(){

    $.ajax({
        url: "https://wikin.kr:443/price_data/" + spiritCode,
        method: "GET",
        success: function(data) {
            var chartData = [];
            var chartXdata = []

            console.log(data);
            console.log("추세데이타");

            for (var i in data) {
                //prev += 5 - Math.random() * 10;
                xdate = data[i].date
                chartData.push(data[i].price)
                chartXdata.push(xdate.substr(0,10))
            }

            console.log(data);
            console.log("추세데이타");


            var ctx = document.getElementById('quoteChart');

            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: chartXdata,
                    datasets: [{
                        borderColor: 'rgba(255, 99, 132, 1)',
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                        borderWidth: 1,
                        radius: 0,
                        label: false,
                        data: chartData,
                        fill:true
                    }]
                },
                options: {

                    interaction: {
                        intersect: false
                    },
                    plugins: {
                        legend: false,
                        tooltip:{
                            displayColors:false,
                            callbacks: {
                                label: function (context) {
                                    var label = context.dataset.label || '';

                                    if (label) {
                                        label += ': ';
                                    }
                                    if (context.parsed.y !== null) {
                                        label += new Intl.NumberFormat('en-US', { style: 'currency', currency: 'KRW' }).format(context.parsed.y);
                                    }
                                    return label;
                                }
                            }
                         /*   backgroundColor:'#fff8bd',
                            titleColor:'red',
                            bodyColor: 'blue'*/
                        },
                    },
                    scales: {
                        xAxes: {
                            display: false
                        },
                        yAxes:{
                            beginAtZero: true,
                            grid:{
                                borderColor: 'white',
                                tickColor:'white',
                                drawOnChartArea: false
                            },
                            position: 'right'
                        }
                    }
                }
            })

            $.ajax({
                url: "https://wikin.kr:443/taste_data/"+ spiritId,
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