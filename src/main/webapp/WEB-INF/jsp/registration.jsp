
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="https://kit.fontawesome.com/7d33ba923d.js" crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/styles/login-style.css">
    <title>login</title>
</head>
<body>
<header>
    <a href="#" style="text-decoration: none;">
        <div class="logo">
            Fitness center
        </div>
    </a>
    <div class="language">
        <div class="position-ru"> <button class="form-button-ru">ru</button> </div>
        <div class="position-be"> <button class="form-button-by">by</button> </div>
        <div class="position-en"> <button class="form-button-en">en</button> </div>
    </div>
</header>
<main >
    <div class="modal">
        <form action="controller?command=registerClient" method="post">

            <label class="size-lable-name" for="fname" >LOGIN</label><br>
            <input class="form-login" required type="text" id="lname" name="login" ><br><br>

            <label class="size-lable-name" for="fname">PASSWORD</label><br>
            <input class="form-password" required type="password" id="lname" name="password" ><br><br>

            <label class="size-lable-name" for="fname" >NAME</label><br>
            <input class="form-login"  required type="text" id="lname" name="name" ><br><br>

            <label class="size-lable-name" for="fname" >SURNAME</label><br>
            <input class="form-login" required type="text" id="lname" name="surname" ><br><br>
            <div class="text-checbox">
                <input class="checkbox-reg" type="checkbox" name="gender" id=""> Male
            </div>
            <div class="text-checbox">
                <input class="checkbox-reg" type="checkbox" name="gender" id=""> Female
            </div>

            <button class="form-button-reg" type="submit" name="submit-log-in">
                SIGN UP
            </button>
        </form>
    </div>

</main>
<div class="line">

</div>
<div class="footer-message">
    <a href="https://www.facebook.com/#!/"> <i class="fab fa-facebook-f"></i></a>
    <a href="https://www.instagram.com/"> <i class="fab fa-instagram"></i> </a>
    <a href="https://mobile.twitter.com/i/flow/login">  <i class="fab fa-twitter"></i> </a>
    <a href="https://support.google.com/answer/2451065?hl=en"> <i class="fab fa-google-plus-g"></i> </a>
    <a href="https://www.pinterest.com/"> <i class="fab fa-pinterest"></i> </a>
    <a href="https://vimeo.com/"> <i class="fab fa-vimeo-v"></i> </a>
    <a href="https://www.linkedin.com/uas/login-submit">  <i class="fab fa-linkedin-in"></i> </a>
    <a href="https://www.youtube.com/">  <i class="fab fa-youtube" ></i> </a>
</div>
</body>
</html>