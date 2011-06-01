$(document).ready(function() {
	$('a').imgPreview({
	     containerID: 'imgPreviewWithStyles'
		});
    $("#organic").attr('checked',true);

	document.getElementsByName("Actions")[0].checked = true;
    checkedAll($("#ActionsForm"),true);
    
    });



 function initialize() {
    var latlng = new google.maps.LatLng(-34.397, 150.644);
    var myOptions = {
      zoom: 8,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"),
        myOptions);
  }

function changeText(id) {
	if ($(id).val()=="Try an Atmospheric Chemistry Example") {
		$("textarea").val("Ambient formaldehyde concentrations are reported from the North Atlantic Marine Boundary Layer Experiment (NAMBLEX) campaign at Mace Head on the west coast of Eire during August 2002. The results from two techniques, using direct determination via gas chromatography and the Hantzsch technique, show similar trends but a significant off set in concentrations. For westerly air flows characteristic of the marine boundary layer, formaldehyde concentrations from the gas chromatographic and Hantzsch technique ranged from 0.78-1.15 ppb and 0.13-0.43 ppb, respectively. Possible reasons for the discrepancy have been investigated and are discussed, however, no satisfactory explanation has yet been found. In a subsequent laboratory intercomparison the two techniques were in good agreement. The observed concentrations have been compared with previous formaldehyde measurements in the North Atlantic marine boundary layer and with other measurements from the NAMBLEX campaign. The measurements from the Hantzsch technique and the GC results lie at the lower and upper ends respectively of previous measurements. In contrast to some previous measurements, both techniques show distinct diurnal profiles with day maxima and with an amplitude of approximately 0.15 ppb. Strong correlations were observed with ethanal concentrations measured during NAMBLEX and the ratio of ethanal to formaldehyde determined by the gas chromatographic technique is in good agreement with previous measurements. Some simple box modelling has been undertaken to investigate possible sources of formaldehyde. Such models are not able to predict absolute formaldehyde concentrations as they do not include transport processes, but the results show that oxygenated VOCs such as ethanal and methanol are very significant sources of formaldehyde in the air masses reaching Mace Head .") ; 	
	    $(id).val("Try an Organic Chemistry Example");
	    $("#organic").attr('checked',false);
	    $("#atmospheric").attr('checked',true);
	}
	else {
		$("textarea").val("To a stirred solution of 4-hydroxypiperidine (0.97 g, 9.60 mmol) in anhydrous dimethylformamide (20 mL) at 0°C was added 1-(bromomethyl)-4-methoxybenzene (1.93 g, 9.60 mmol) and triethylamine (2.16 g, 21.4 mmol). The reaction mixture was then warmed to room temperature and stirred overnight. After this time the mixture was concentrated under reduced pressure and the resulting residue was dissolved in ethyl acetate (40 mL), washed with water (20 mL) and brine (20 mL) before being dried over sodium sulfate. The drying agent was filtered off and the filtrate concentrated under reduced pressure. The residue obtained was purified by flash chromatography (silica gel, 0-5% methanol/methylene chloride) to afford 1-(4-methoxybenzyl)piperidin-4-ol as a brown oil (1.70 g, 80%).");
		$(id).val("Try an Atmospheric Chemistry Example");
	    $("#organic").attr('checked',true);
	    $("#atmospheric").attr('checked',false);
	}

}

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
	
