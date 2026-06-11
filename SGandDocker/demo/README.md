# Spring Boot CRUD API Sample

## 概要

Spring Bootを利用したCRUD APIのサンプルアプリケーションです。

学習内容

* Spring Boot REST API
* Controller / Service / Repository構成
* Dockerコンテナ化
* Amazon ECR
* Amazon ECS(Fargate)

## API一覧

### GET

* GET /user/{name}
* GET /users?name={name}

### POST

* POST /user

### PUT

* PUT /user/{name}

### DELETE

* DELETE /user/{name}

## アーキテクチャ

Controller
↓
Service
↓
Repository

## 動作環境

* Java 17
* Spring Boot
* Docker
* Amazon ECR
* Amazon ECS(Fargate)

## 今後の予定

* DynamoDB連携
* Validation追加
* APIテスト強化
