$(document).ready(function() {
    document.getElementsByName("Actions")[0].checked = true;
    checkedAll($("#ActionsForm"),true);
});

function checkedAll(id,checked) {
    id.children().each(function() {

    	
        var child = $(this);
        
        var checkboxes = $(this).closest('form').find(':checkbox');
        checkboxes.attr('checked', checked);
        highlight(child.attr("name"),checked);
    });

}


 function highlight (name,checked) {
     $('span[name="'+name+'"]').each(function () {
        if (checked){
           $(this).addClass(name);}
        else {   
            $(this).removeClass(name);}
}
);
 }
