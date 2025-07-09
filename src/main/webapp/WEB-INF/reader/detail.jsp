<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>投稿詳細</title>
    <link rel="stylesheet" href="/css/common/alert.css">
    <link rel="stylesheet" href="/css/reader/detail.css">
</head>
<body>
    <h1>投稿詳細</h1>
    <%@ include file="/WEB-INF/common/alert.jsp" %>
    <div class="post-detail">
        <h2>${post.postTitle}</h2>
        <p>${post.postText}</p>
        <p>投稿日時: ${post.createdAt}</p>
    </div>
</body>
</html>