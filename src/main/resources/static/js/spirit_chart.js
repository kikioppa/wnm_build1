

window.onload = function(){

    $.ajax({
        url: "http://localhost:8080/price_data/" + spiritCode,
        method: "GET",
        success: function(data) {
            var chartData = [];
            var chartXdata = []
            console.log(data);
            console.log("추세데이타");
            for (var i in data) {
                chartData.push(data[i].price)
                chartXdata.push(data[i].date)
            }

            var ctx = document.getElementById('quoteChart');

            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: chartXdata,
                    datasets: [{
                        label: '거래금액 :',
                        data: chartData,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(0, 0, 0, 0)',
                            'rgba(0, 0, 0, 0)',
                            'rgba(0, 0, 0, 0)',
                            'rgba(0, 0, 0, 0)',
                            'rgba(0, 0, 0, 0)',
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(255, 99, 132, 1)',
                        ],
                        pointStyle:'crossRot'
                        ,
                        borderWidth: 2
                        ,
                        lineTension:0.4
                    }]
                },
                options: {

                    responsive:true,
                    scales: {
                        xAxes: [{
                            gridLines: {

                                drawOnChartArea: false
                            },
                            display:false
                        }],
                        yAxes: [{
                            ticks: {
                                beginAtZero: true,
                                fontColor: "#8e7b96",
                                padding:10
                            },
                            gridLines: {
                                drawBorder: false,
                                drawTicks:false,
                                drawOnChartArea: false
                            },
                            position:'right'
                        }]
                    }
                }
            })

            $.ajax({
                url: "http://localhost:8080/taste_data/"+ spiritId,
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
                    console.log(result[i]+"냥냥");

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