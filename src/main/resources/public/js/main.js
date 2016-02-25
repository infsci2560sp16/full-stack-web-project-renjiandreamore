function show()
{
    var box = document.getElementById("box");
    var text = box.innerHTML;
    var newBox = document.createElement("div");
    var btn = document.createElement("a");
    newBox.innerHTML = text.substring(0,20);
    btn.innerHTML = text.length > 20 ? "...more" : "";
    btn.href = "###";
    btn.onclick = function(){
        if(btn.innerHTML == "...more")
        {
            btn.innerHTML = "hide";
            newBox.innerHTML = text;
        }
        else
        {
            btn.innerHTML = "...more";
            newBox.innerHTML = text.substring(0,20);
        }
    };
    box.innerHTML = "";
    box.appendChild(newBox);
    box.appendChild(btn);
}
window.onload=function()
{
    show();
};