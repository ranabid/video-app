<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>App - upload file</title>
<body>
	<h3>File Upload:</h3>
	Select a file to upload:
	<br />
	<form id="fileUploadForm" method="POST" enctype="multipart/form-data">
		<input type="file" name="file" size="50" /> <br /><br /> <input
			id="btnSubmit" type="submit" value="Upload File" />
	</form>
	<br />
	<pre>
    <span id="result"></span>
</pre>
</body>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript">
	$('#btnSubmit').click(function(event) {
		event.preventDefault();
		sendData();
	});

	function sendData() {
		var form = $('#fileUploadForm')[0];
		var data = new FormData(form);
		$('#btnSubmit').prop("disabled", true);

		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "upload",
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {
				$('#result').text(data);
				$('#btnSubmit').prop("disabled", false);
			},
			error : function(err) {
				$('#result').text(err.responseText);
				$('#btnSubmit').prop("disabled", false);
			}
		});
	}
</script>
</html>