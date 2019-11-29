<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<%%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            $("#inp").click(function () {
                var param = $("#inn").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/poems/poem",
                    data: {"query": param},
                    type: "post",
                    dataType: "JSON",
                    success: function (data) {
                        $("#poem_div").empty();
                        $.each(data, function (index, poem) {
                            var hr = $("<hr>")
                            var name = $("<p>").html(poem.name)
                            var author = $("<p>").html(poem.author)
                            var type = $("<p>").html(poem.type)
                            var content = $("<p>").html(poem.content)
                            var href = $("<a>").html(poem.href).attr("href", poem.href)
                            var authordes = $("<p>").html(poem.authordes)
                            $("#poem_div").append(name).append(author).append(type).append(content).append(href).append(authordes).append(hr)

                        })
                    }
                })
            })
        })
    </script>
</head>
<body>
<div align="center">
    <input type="text" id="inn" placeholder="输入搜索内容">
    <input type="submit" id="inp" value="点击搜索">
</div>

<div style="text-align: center" id="poem_div">

</div>
</body>
</html>