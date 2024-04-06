<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Senha Alterada</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333333;
            text-align: center;
            margin-bottom: 30px;
        }
        p {
            color: #666666;
            line-height: 1.5;
            margin-bottom: 20px;
        }
        .logo {
            text-align: center;
            margin-bottom: 30px;
        }
        .logo img {
            max-width: 200px;
            max-height: 80px;
        }
        .btn {
            display: inline-block;
            background-color: #ff6347;
            color: #ffffff;
            text-decoration: none;
            padding: 12px 24px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #ff4d2e;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="logo">
        <img src="cid:logo" alt="Logo">
    </div>
    <h1>Senha Alterada</h1>
    <p>Olá, ${recipientName}!</p>
    <p>Informamos que sua senha foi alterada com sucesso em nosso sistema.</p>
    <p>Se você não solicitou essa alteração, recomendamos que entre em contato conosco imediatamente para proteger sua conta.</p>
    <p>Caso tenha sido você quem solicitou a alteração, pode ignorar esta mensagem.</p>
    <p>Aproveite para explorar nossas novidades e promoções:</p>
    <a href="#" class="btn">Ver Promoções</a>
    <p>Atenciosamente,<br>Equipe Wolf E-Commerce</p>
    <img src="cid:image" style="max-width: 100%; height: auto;" alt="Imagem">
</div>
</body>
</html>