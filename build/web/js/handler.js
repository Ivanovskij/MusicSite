function checkEmptyValue() {
    
    var userName = document.getElementById("userlogin");
    var userPass = document.getElementById("userpass");
    
    if (userName.value === "" || userPass.value === "") {
        alert("Заполните все поля для входа в аккаунт");
        userName.focus();
        return false;
    }
    return true;
}
