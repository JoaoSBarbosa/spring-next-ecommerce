<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Código Expirado</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 40px auto;
            padding: 40px;
            background-color: #ffffff;
            border-radius: 12px;
            box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333333;
            text-align: center;
            margin-bottom: 30px;
        }
        p {
            color: #666666;
            line-height: 1.6;
            margin-bottom: 24px;
        }
        .logo {
            text-align: center;
            margin-bottom: 40px;
        }
        .logo img {
            max-width: 240px;
            max-height: 100px;
        }
        .btn {
            display: inline-block;
            background-color: #FF5722;
            color: #ffffff;
            text-decoration: none;
            padding: 14px 28px;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #E64A19;
        }
        .image {
            text-align: center;
            margin-top: 40px;
        }
        .image img {
            max-width: 300px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="logo">
        <img src="cid:logo" alt="Logo">
    </div>
    <h1>Código expirado</h1>
    <p>Olá, ${recipientName}!</p>
    <p>Lamentamos informar que o código de recuperação de senha que você solicitou expirou.</p>
    <p>Por motivos de segurança, os códigos de recuperação de senha são válidos por apenas 15 minutos após o envio.</p>
    <p>Para continuar o processo de redefinição de senha, clique no botão abaixo para solicitar um novo código.</p>
    <a href="#" class="btn">Solicitar novo código</a>
    <p>Caso você não tenha solicitado a redefinição de senha, entre em contato conosco imediatamente para proteger sua conta.</p>
    <p>Atenciosamente,<br>Equipe Wolf E-Commerce</p>
    <div class="image">
        <img src="cid:image" alt="Imagem de alteração de senha">
    </div>
</div>
</body>
</html>