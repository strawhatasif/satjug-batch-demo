/****For BATCH_JOB table****/

USE [BATCH]
GO

/****** Object:  Table [dbo].[BATCH_JOB]    Script Date: 3/11/2020 8:13:28 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[BATCH_JOB](
	[JOB_NM] [nchar](30) NOT NULL,
	[EXECUTION_ID] [char](10) NULL,
	[JOB_DATE] [datetime] NULL,
	[JOB_STATUS] [varchar](10) NULL
) ON [PRIMARY]
GO

/***For account table***/

USE [BATCH]
GO

/****** Object:  Table [dbo].[ACCOUNT]    Script Date: 3/11/2020 8:18:53 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ACCOUNT](
	[ID] [int] NOT NULL,
	[NAME] [varchar](50) NOT NULL,
	[PRODUCT] [varchar](50) NOT NULL,
	[NOTES] [varchar](max) NULL,
	[STATUS] [varchar](10) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO