-------------------------------------------------------------------------------
-- Initial data
-------------------------------------------------------------------------------
-- Title
MERGE INTO title (name) KEY(name) VALUES ('Dr.')

-------------------------------------------------------------------------------
-- NameAddition
MERGE INTO name_addition (name) KEY(name) VALUES ('van')
MERGE INTO name_addition (name) KEY(name) VALUES ('von')
MERGE INTO name_addition (name) KEY(name) VALUES ('de')