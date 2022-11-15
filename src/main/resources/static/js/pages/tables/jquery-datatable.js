$(function () {
    $('.js-basic-example').DataTable({
        responsive: true
    });

    //Exportable table
    $('.js-exportable').DataTable({
        dom: 'Bfrtip',
        responsive: true,
        
        buttons: [
        	{
        	extend:  'excel'
        	}
        ]
    });
    
    $('.js-exportable-client').DataTable({
        dom: 'Bfrtip',
        responsive: true,
        
        buttons: [
        	{
        	extend:  'excel',
        		 exportOptions: {
                     columns: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]
                 }
        	}
        ]
    });
    
});