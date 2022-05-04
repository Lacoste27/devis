<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Devis | Liste Client</title>
</head>
<body>
    <?php foreach ($list as $client) {?>
        <ul>
            <a href="index.php?action=byId&&id=<?=$client->id?>"> <li><?=$client->nom?></li></a>
        </ul>
    <?php }?>
    <table class="table table-light">
        <thead class="thead-light">
            <tr>
                <th>Nom</th>
                <th>Ref demande</th>
                <th>Date demande</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td></td>
            </tr>
        </tbody>
    </table>
</body>
</html>