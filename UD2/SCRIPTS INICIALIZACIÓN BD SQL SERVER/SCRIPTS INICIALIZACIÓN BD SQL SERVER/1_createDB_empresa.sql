USE [master]
GO

IF NOT EXISTS (  SELECT * FROM sys.databases  WHERE name = 'empresa')
BEGIN

CREATE DATABASE [empresa];
END