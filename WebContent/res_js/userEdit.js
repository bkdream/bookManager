//1.获取表单组件对象
var userxmObj = document.getElementById("userxm");
var birthdayObj = document.getElementById("birthday");
var emailObj = document.getElementById("email");

var userxmMsg = document.getElementById("userxmMsg");
var birthdayMsg = document.getElementById("birthdayMsg");
var emailMsg = document.getElementById("emailMsg");

//2.表单组件事件
//2.1用户真实姓名事件处理
userxmObj.addEventListener('keyup', function() {
    checkUserxm(userxmObj,userxmMsg);
});

//2.2用户出生日期事件处理
birthdayObj.addEventListener('blur', function() {
    checkBirthday(birthdayObj,birthdayMsg);
});

//2.3用户邮箱事件处理
emailObj.addEventListener('keyup', function() {
    checkEmail(emailObj, emailMsg);
});

//3.验证用户个人信息修改事件表单处理
function checkUserEditForm() {
    var bUserxm = checkUserxm(userxmObj, userxmMsg);
    var bBirthday = checkBirthday(birthdayObj, birthdayMsg);
    var bEmail = checkEmail(emailObj, emailMsg);

    return bUserxm && bBirthday && bEmail;
}

//3.2验证用户个人信息修改事件表单处理
// var btnUserEditObj = document.getElementById('btnUserEdit');
// btnUserEditObj.addEventListener('click', function() {

//     var bUserxm = checkUserxm(userxmObj, userxmMsg);
//     var bBirthday = checkBirthday(birthdayObj, birthdayMsg);
//     var bEmail = checkEmail(emailObj, emailMsg);

//     if (bUserxm && bBirthday && bEmail) {
//         //若为ture, 执行submit
//         document.getElementById('userEditForm').submit();
//     }
// });