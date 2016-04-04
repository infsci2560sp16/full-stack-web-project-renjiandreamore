<!DOCTYPE html>

<html>

<body>
<h2>User</h2>
<h3>${method} JSON</h3>
<p>
Name: <span id="name"></span><br />
ID: <span id="id"></span><br />
Gender: <span id="gender"></span><br />

</p>
<p>${result}</p>
<script>
var JSONObject= ${result};

document.getElementById("name").innerHTML=JSONObject.name
document.getElementById("id").innerHTML=JSONObject.id
document.getElementById("gender").innerHTML=JSONObject.gender

</script>



</body>
</html>
