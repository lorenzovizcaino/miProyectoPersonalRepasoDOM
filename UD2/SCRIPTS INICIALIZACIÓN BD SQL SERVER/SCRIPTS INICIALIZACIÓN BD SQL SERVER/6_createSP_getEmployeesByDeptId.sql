USE [empresa]
GO

/****** Object:  StoredProcedure [dbo].[getEmployeesByDepartmentId]    Script Date: 01/12/2022 17:04:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		mfernandez
-- Create date: 11-11-2022
-- Description:	Obtiene la lista de empleados por el id de departamento
--              
-- =============================================
CREATE OR ALTER PROCEDURE [dbo].[getEmployeesByDepartmentId]
	-- Add the parameters for the stored procedure here
	@deptId int
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	--https://learn.microsoft.com/es-es/sql/t-sql/statements/set-nocount-transact-sql?view=sql-server-ver16
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT ENAME 
	FROM EMP
	WHERE DEPTNO=@deptId
	ORDER BY ENAME
END
GO


