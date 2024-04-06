<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seu Código de Alteração de Senha</title>
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
        .image img {
            max-width: 400px;
            height: auto;
        }
        .code-box {
            background-color: #f5f5f5;
            padding: 20px;
            border-radius: 8px;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            color: #333333;
            margin-bottom: 30px;
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
    </style>
</head>
<body>
<div class="container">
    <div class="logo">
        <img src="cid:logo" alt="Logo">
    </div>
    <h1>Seu código de alteração de senha</h1>
    <p>Olá, ${recipientName}!</p>
    <p>Você solicitou a alteração de sua senha. Aqui está o seu código de verificação:</p>
    <div class="code-box">${code}</div>
    <p>Use este código para concluir o processo de alteração de senha.</p>
    <p>Se você não solicitou essa alteração, entre em contato conosco imediatamente para proteger sua conta.</p>
    <a href="#" class="btn">Alterar Senha</a>
    <p>Atenciosamente,<br>Equipe Wolf E-Commerce</p>
    <div class="image">
        <img src="cid:image" alt="Key">
    </div>
</div>
</body>
</html>