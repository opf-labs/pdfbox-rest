$(document).on('change', '.btn-file :file', function() {
    var input = $(this),
        numFiles = input.get(0).files ? input.get(0).files.length : 1,
        label = input.val().replace(/\\/g, '/').replace(/.*\//, ''),
        rusha = new Rusha(),
        file = input.get(0).files[0],
        reader = new FileReader();
    reader.onload = function(e) {
        var rawData = reader.result;
        var digest = rusha.digest(rawData);
        input.trigger('fileselect', [numFiles, label, digest]);
    }
    reader.readAsBinaryString(file);
});

$(document).ready( function() {
    $('.btn-file :file').on('fileselect', function(event, numFiles, label, digest) {
        var input = $(this).parents('.input-group').find(':text'),
            log = numFiles > 1 ? numFiles + ' files selected' : label;
        
        if( input.length ) {
            input.val(log);
        } else {
            if( log ) alert(log);
        }
        
        $("#digest").val(digest);
        var formData = new FormData($('form')[0]);

        $.ajax({
            url:   'http://localhost:8080/pdfbox/validate',
            type:  'POST',
            data:  formData,
            dataType: "xml",
            contentType: false,
            processData: false,
        success : function (data, textStatus, jqXHR) {
            console.log(jqXHR);
           $("#results").empty();
           $("#results").text(jqXHR.responseText);
           $("#results").append('<br>');
           $("#results").append(digest);
        },
        error : function (jqXHR, textStatus, errorThrown) {
            console.log('uploadAttachment error: ' + textStatus + errorThrown);
            console.log(jqXHR);
            console.log('uploadAttachment error: ' + textStatus + errorThrown);

        }
        });
    });
});


function successHandler(data, status, jqXhr) {
    alert(status);
}