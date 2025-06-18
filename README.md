# JSP / Servlet Example

**Docker / Docker Compose / Gradle / Tomcat / MySQL** を用いた **JSP / Servlet** Web アプリケーションのサンプルです。

## 必要なもの

- **Docker**

- **Docker Compose**

入ってない人たちは、 https://www.docker.com/ に行きましょう。 

## ローカルで動かすために

### **$ git clone** or ($ ghq get)

$ git clone （$ ghq get） しましょう。

``` bash
$ git clone https://github.com/cs-u-gakugei-ac-jp/java_app_sample
($ ghq get https://github.com/cs-u-gakugei-ac-jp/java_app_sample)
```

### 当該ディレクトリに移動

がんばりましょう。

``` bash
$ cd "ひとそれぞれ"/java_app_sample
```

### Docker Compose Build

下記のコマンドを実行することにより、 **MySQL コンテナ** と **Tomcat コンテナ** と **Gradle コンテナ** を作成し始めます。

```bash
$ docker-compose build
```

### Docker Compose Up

下記のコマンドを実行することにより、 **MySQL コンテナ** と **Tomcat コンテナ** と **Gradle コンテナ** が起動し、アプリケーションが動作し始めます。

``` bash
$ docker-compose up
```

## もろもろ確認

### Web アプリの動作確認

任意のブラウザで、 **http://localhost:8888/hello** にアクセスすれば、 "Hello World !" が表示されるはずです。

### DB の動作確認

以下のパラメータで、 DB との疎通確認が行えるはずです。データベースの指定は、任意ですがデフォルトでは以下のような **java_app_sample** という DB が生成されているはずです。

- **HOST:** 127.0.0.1
- **USER:** root
- **PASSWORD:** root
- **PORT:** 3333

## プログラムのコピー方法

以下にパクリ方書いておきます。（バグがあったらごめんなさい。）

### 空の GitHub リポジトリ作成

自身で、 GitHub のリポジトリを作成しましょう。  
このとき、かならず空のリポジトリを作成するようにしてください。（.gitignore ファイルや、 README.md ファイルは作成しちゃダメ。）

### regenerate.sh の実行

このリポジトリをコピーするシェルスクリプトを書いておいたので、これを実行するとよきです。  
出力の指示に従い、入力を行えば新たにプロジェクトを生成できるはずです。

`$ sh bin/regenerate.sh`
