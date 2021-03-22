$(document).ready( function () {
	 var table = $('#employeesTable').DataTable({
			"sAjaxSource": "/rankshow",
			"sAjaxDataProp": "",

			"aoColumns": [
			    { "mData": "id"},
		      { "mData": "name" }
			]
	 })
});