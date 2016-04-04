<!DOCTYPE html>

<html>

<body>
<h2>Menu TEST RESTFul GET XML</h2>

<textarea rows="3" cols="20">${result}</textarea>
<script>
text = "${result}";

try //Internet Explorer
{
xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
xmlDoc.async="false";
xmlDoc.loadXML(text);
}
catch(e)
{
try //Firefox, Mozilla, Opera, etc.
{
parser=new DOMParser();
xmlDoc=parser.parseFromString(text,"text/xml");
}
catch(e) {alert(e.message)}
}
try
{

document.write("<br>Name: ");
document.write(xmlDoc.getElementsByTagName("name")[0].firstChild.nodeValue);
document.write("<br>Id: ");
document.write(xmlDoc.getElementsByTagName("id")[0].firstChild.nodeValue);
document.write("<br>Gender: ");
document.write(xmlDoc.getElementsByTagName("gender")[0].firstChild.nodeValue);

}
catch(e) {alert(e.message)}
</script>



</body>
</html>
