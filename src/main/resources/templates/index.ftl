<#import "/spring.ftl" as spring/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Test</title>
</head>
<body>

<div class="header">
<#if greeting.name??>
    <h1 class="title">Hey ${greeting.name}</h1>
</#if>
</div>

<form action="<@spring.url '/'/>" method="post">
<@spring.formInput 'greeting.name'/>
    <button type="submit" name="submit">Submit</button>
    <button type="button" name="ajax" onclick="ajaxSubmit()">Ajax Submit</button>
</form>

<script src="https://code.jquery.com/jquery-3.1.0.min.js" type="application/javascript"></script>

<script type="application/javascript">
    function ajaxSubmit() {
        var data = {
            'name': $('input[name=name]').val()
        };

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: 'POST',
            url: '/ajax',
            dataType: 'json',
            data: JSON.stringify(data)
        }).done(function (data) {
            $('.header').append("<h1 class='title'>Hey " + data.name + "</h1>");
        });
    }
</script>
</body>
</html>