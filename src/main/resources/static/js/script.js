/*------------------------------------------------Select All Checkbox---------------------------------------------*/
var app = {};
app.global = {};
app.data = {};
app.ui = {
	toggleCheckbox : function(ele) {
		var checkboxes = document.getElementsByTagName('input');
		var checkboxes = $(ele).closest('table').find(
				'tbody input[type=checkbox]');
		if (ele.checked) {
			for (var i = 0; i < checkboxes.length; i++) {
				if (checkboxes[i].type == 'checkbox') {
					checkboxes[i].checked = true;
				}
			}
		} else {
			for (var i = 0; i < checkboxes.length; i++) {
				// console.log(i)
				if (checkboxes[i].type == 'checkbox') {
					checkboxes[i].checked = false;
				}
			}
		}
	}
}

/*-- ----------------------------------------------------------------------------------------------------------*/
/*-- ----------------------------------------------Prepare IC--------------------------------------------------*/

function Prepare() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			billNo : $(value).closest('tr').find('td[data-bNo]').html(),
			investigationId : $(value).closest('tr').find('td[data-inId]')
					.html(),
			calculatedAmount : $(value).closest('tr').find(
					'td input[data-icAmount]').val(),
		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/prepare',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Cancel(Prepare) IC---------------------------------------------*/

function CancelPrepare() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			billNo : $(value).closest('tr').find('td[data-bNo]').html(),
			investigationId : $(value).closest('tr').find('td[data-inId]')
					.html(),
			calculatedAmount : $(value).closest('tr').find(
					'td input[data-icAmount]').val(),
		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/notprepare',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Hold(Prepare) IC---------------------------------------------*/

function HoldPrepare() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			billNo : $(value).closest('tr').find('td[data-bNo]').html(),
			investigationId : $(value).closest('tr').find('td[data-inId]').html(),
			calculatedAmount : $(value).closest('tr').find('td input[data-icAmount]').val(),
			
		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/hold/prepare',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Allocate IC---------------------------------------------*/

function Allocate() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			icId : $(value).closest('tr').find('td[data-icId]').html(),	
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			billNo : $(value).closest('tr').find('td[data-bNo]').html(),
			investigationId : $(value).closest('tr').find('td[data-inId]').html(),
			preparedAmount : $(value).closest('tr').find('td input[data-icAmount]').val(),

		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/allocate',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Cancel(Allocate) IC---------------------------------------------*/

function CancelAllocate() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			billNo : $(value).closest('tr').find('td[data-bNo]').html(),
			investigationId : $(value).closest('tr').find('td[data-inId]')
					.html(),
			preparedAmount : $(value).closest('tr').find('td input[data-icAmount]').val(),

		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/allocatecancel',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Not Allocate(Move To Prepare) IC---------------------------------------------*/

function AllocateToPrepare() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			billNo : $(value).closest('tr').find('td[data-bNo]').html(),
			investigationId : $(value).closest('tr').find('td[data-inId]')
					.html(),
			preparedAmount : $(value).closest('tr').find(
					'td input[data-icAmount]').val(),

		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/notallocate',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Hold(Allocate) IC---------------------------------------------*/

function HoldAllocate() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			billNo : $(value).closest('tr').find('td[data-bNo]').html(),
			investigationId : $(value).closest('tr').find('td[data-inId]').html(),
			calculatedAmount : $(value).closest('tr').find('td input[data-icAmount]').val(),
		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/hold/allocate',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Approve IC---------------------------------------------*/

function Approve() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			icId : $(value).closest('tr').find('td[data-iId]').html(),
			proId : $(value).closest('tr').find('td[data-pId]').html(),
			proName : $(value).closest('tr').find('td[data-pName]').html(),
			clientId : $(value).closest('tr').find('td[data-cId]').html(),
			clientName : $(value).closest('tr').find('td[data-cName]').html(),
			allocatedAmount : $(value).closest('tr').find(
					'td input[data-icAmount]').val(),
		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/approve',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Cancel(Approve) IC---------------------------------------------*/
function CancelApprove() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			icId : $(value).closest('tr').find('td[data-iId]').html(),
			proId : $(value).closest('tr').find('td[data-pId]').html(),
			clientId : $(value).closest('tr').find('td[data-cId]').html(),
			allocatedAmount : $(value).closest('tr').find(
					'td input[data-icAmount]').val(),
		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/approvecancel',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*-------------------------------Not Approve(Move To Allocate) IC---------------------------------------------*/
function ApproveToAllocate() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			icId : $(value).closest('tr').find('td[data-iId]').html(),
			proId : $(value).closest('tr').find('td[data-pId]').html(),
			clientId : $(value).closest('tr').find('td[data-cId]').html(),
			allocatedAmount : $(value).closest('tr').find('td input[data-icAmount]').val(),
		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/notapprove',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Hold(Approve) IC---------------------------------------------*/

function HoldApprove() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			billNo : $(value).closest('tr').find('td[data-bNo]').html(),
			investigationId : $(value).closest('tr').find('td[data-inId]').html(),
			calculatedAmount : $(value).closest('tr').find('td input[data-icAmount]').val(),
		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/hold/approve',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*---------------------------------------------------ROUND OFF NET AMOUNT--------------------------------------------*/

function roundOff() {

	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var percentage = document.getElementById('percentageRoundOff').value;

	$.each(checkedRows, function(index, value) {
		var item = {
				NetAmount : $(value).closest('tr').find('td[data-netAmount]').html(),
				
				 MinAmount : $(value).closest('tr').find('td[data-minCost]').html(),
		}
		var result1 = item.NetAmount / 100 * percentage;
		var data1 = result1 +".00";
		$(value).closest('tr').find('td[data-netAmount]').html(data1)
		
		var result2 = data1-item.MinAmount;
		var data2 = result2 +".00";
		$(value).closest('tr').find('td input[data-icAmount]').val(data2)
		
	});

}

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------ROUND OFF BACK NET AMOUNT--------------------------------------------*/

	function roundOffBack() {
	
		var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
		var percentage = document.getElementById('percentage').value;
	
		$.each(checkedRows, function(index, value) {
			var item = {
				NetAmount : $(value).closest('tr').find('td[data-netAmount]').html(),
					
				MinAmount : $(value).closest('tr').find('td[data-minCost]').html(),
			}
			var result1 = item.NetAmount / percentage * 100;
			var data1 = result1 +".00";
			$(value).closest('tr').find('td[data-netAmount]').html(data1)
			
			var result2 = Math.round(data1) - Math.round(item.MinAmount);
			var data2 = result2 +".00";
			$(value).closest('tr').find('td input[data-icAmount]').val(data2)
			
	
		});
	}

/*------------------------------------------------------------------------------------------------------------*/
/*----------------------------------Round Down of 10's/50's/100's---------------------------------------------*/

function roundDown() {

	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var r = document.getElementById('rDown').value;

	$.each(checkedRows, function(index, value) {
		var item = {
			calculatedAmount : $(value).closest('tr').find(
					'td input[data-icAmount]').val(),
		}
		var result = Math.floor(item.calculatedAmount / r) * r;
		var data = result +".00";
		$(value).closest('tr').find('td input[data-icAmount]').val(data)

	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*-----------------------------------------No Numeric value In Input Type Text--------------------------------*/
$(function() {
	$('.integerInput').on('input', function() {
		this.value = this.value.replace(/[^\d]/g, '');// numbers and decimals
														// only

	});
});
/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Un-Hold(ALL) IC---------------------------------------------*/

function UnHold() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			billNo : $(value).closest('tr').find('td[data-bNo]').html(),
			investigationId : $(value).closest('tr').find('td[data-inId]').html(),
			billinvestStatus : $(value).closest('tr').find('td[data-billinvestStatus]').html(),
			calculatedAmount : $(value).closest('tr').find('td input[data-icAmount]').val(),
		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/unhold',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*----------------------------------------------IsEligibleIc(Reconciliation)---------------------------------------------------*/

function IsEligibleIc() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	var items = [];
	$.each(checkedRows, function(index, value) {
		var item = {
			ibid : $(value).closest('tr').find('td[data-ibId]').html(),
			billNo : $(value).closest('tr').find('td[data-bNo]').html(),
			investigationId : $(value).closest('tr').find('td[data-inId]').html(),
			billinvestStatus : $(value).closest('tr').find('td[data-billinvestStatus]').html(),
			calculatedAmount : $(value).closest('tr').find('td input[data-icAmount]').val(),
		}
		items.push(item);
	});
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		type : 'POST',
		url : '/ic/noneligibleic/iseligible',
		data : JSON.stringify(items)
	});
	$(document).ajaxStop(function() {
		window.location.reload();
	});
}

/*------------------------------------------------------------------------------------------------------------*/
/*-----------------------------------------Serial Number for Table----------------------------------------------*/
$(document).ready(function(){
var addSerialNumber = function () {
    var i = 1
    $('table tbody tr').each(function(index) {
        $(this).find('td:nth-child(1)').html(index+1);
    });
};
addSerialNumber();

});
/*------------------------------------------------------------------------------------------------------------*/
/*-----------------------------------------Client Master Popup(Percentage)------------------------------------------------*/

/*function disableField(val) {
    console.log(val);
    
    var Type = document.getElementById("editType");
  if( val.length = "DOCTOR"  ) {
	  Type.disabled = true;
  }else{
	  Type.disabled = false;    
  }
}*/

/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------Selected IC Amount-------------------------------------*/

function SelectedIcAmount() {
	var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
	//var r = document.getElementById('sumSelectedIcAmount').value;

	var result=0;
	
	$.each(checkedRows, function(index, value) {
		var calculatedAmount = $(value).closest('tr').find('td input[data-icAmount]').val();
		
		result += Math.floor(calculatedAmount);
		var data = result +".00";
		
		document.getElementById("sumSelectedIcAmount").value = data;
		//$(value).closest('tr').find('td input').value(data)
 
	});
	}
	
/*function TotalIc() {
		var checkedRows = $('table tbody tr td input[type=checkbox]:checked');
		var items = [];
		var total = 0;
		$.each(checkedRows, function(index, value) {
			var item = {
					calculatedAmount : $(value).closest('tr').find('td input[data-icAmount]').val()
				}
		
		//$('div div div h2 span').eq(checkedRows).text('Total: ' + total);
		var data = total;
		
		$(value).closest('div').find('h2 input[selectedIcAmount]').val(data);
		});
	};
*/
/*------------------------------------------------------------------------------------------------------------*/
