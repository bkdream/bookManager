//头像切换事件
var headpicObj = document.getElementById("headpic");
var picObj = document.getElementById("pic");
var locaObj = window.location;//获取当前url
var contextPath = locaObj.pathname.split("/");//获取当前项目名称
headpicObj.addEventListener('change', function() {
    //使用图片的绝对路径
	picObj.src = "/"+contextPath[1]+"/res_images/face/" + headpicObj.options[headpicObj.selectedIndex].value;
});