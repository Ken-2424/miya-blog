<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MiyaBlog</title>
    <link rel="stylesheet" href="/css/common/alert.css">
    <link rel="stylesheet" href="/css/administer/post/home.css">
</head>
<body>
    <h1>MiyaBlog</h1>
    <p>最新の研究をお伝えします</p>
    <button class="new-post" onclick="location.href='/administer/post/create'">新規作成画面</button>
    <button class="logout" onclick="confirmLogout()">ログアウト</button>
</body>
</html>
