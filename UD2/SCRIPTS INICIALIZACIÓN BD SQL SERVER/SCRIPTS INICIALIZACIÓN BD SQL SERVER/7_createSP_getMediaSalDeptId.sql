USE [empresa]
GO

/****** Object:  StoredProcedure [dbo].[getMediaSalByDeptId]    Script Date: 01/12/2022 17:10:22 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		mfernandez
-- Create date: 2022-11-10
-- Description: Devuelve el salario medio del departamento cuyo Id llega por parámetro
-- =============================================
CREATE OR ALTER PROCEDURE [dbo].[getMediaSalByDeptId]
	-- Add the parameters for the stored procedure here
	@deptId int, 
	@avg money OUTPUT

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT @avg= avg(sal)
	FROM EMP
	WHERE DEPTNO = @deptId

END
GO


