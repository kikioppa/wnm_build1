

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
                    var tastingXdata = ['Fruity (과일향)','Floral(꽃향)','Winey(와인향)','Feinty(잔향)','Sulphury(황향)','Woody(나무향)','Peaty(피트향)'
                        ,'Cereal'];

                    tastingData.push(
                        result.cereal
                        ,result.peaty
                        ,result.fruity
                        ,result.floral
                        ,result.winey
                        ,result.feinty
                        ,result.sulphury
                        ,result.woody

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
                        type: 'radar',
                        data: {
                            labels: tastingXdata,
                            datasets: [{
                                label: 'Simple Whisky Flavour Wheel',
                                data: tastingData,
                                fill: true,
                                backgroundColor: [
                                    'rgba(255, 153, 153, 0.5)',
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
                                ],

                            }]

                        },
                        options:{
                            scale: {
                                gridLines: {
                                    color: ['white', 'white', 'white', 'white', 'white', 'white', 'white', 'white']
                                },
                                pointLabels :{
                                    fontSize: 12,
                                },
                                ticks: {
                                    suggestedMin: 0,
                                    suggestedMax: 10,
                                    display: false,
                                    maxTicksLimit: 5
                                }
                            }



                        }

                    })
                }
            })
        }
    })
};