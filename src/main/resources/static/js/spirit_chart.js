window.onload = function(){
    $.ajax({
        url: "http://localhost:8080/data",
        method: "GET",
        success: function(data) {
            var chartData = [];
            var chartXdata = []
            console.log(data);
            console.log("추세데이타");
            for (var i in data) {
                chartData.push(data[i].martPhone)
                chartXdata.push(data[i].id)
            }

            var ctx = document.getElementById('quoteChart');

            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: chartXdata,
                    datasets: [{
                        label: '# of Votes',
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
                        lineTension:0.0001
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
                url: "http://localhost:8080/data2",
                method: "GET",
                success: function(data) {
                    console.log(data);
                    var tastingData = [];
                    var tastingXdata = [];
                    for (var i in data) {
                        tastingData.push(data[i].martHours)
                        tastingXdata.push(data[i].martName)
                    }
                    console.log(data[i]);


                    var ctx = document.getElementById('tastingChart');

                    var tastingChart = new Chart(ctx, {
                        type: 'polarArea',
                        data: {
                            labels: tastingXdata,
                            datasets: [{
                                label: '# of Votes',
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