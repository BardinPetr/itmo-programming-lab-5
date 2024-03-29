package ru.bardinpetr.itmo.lab5.clientgui.i18n.data;


import java.util.ListResourceBundle;

public class GUITexts extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"dateFormat", "dd.MM.yyyy"},
                {"optionalAnswers.Yes", "Yes"},
                {"optionalAnswers.No", "No"},
                {"optionalAnswers.Ok", "OK"},
                {"generalError", "Something went wrong via sending command"},
                {"ScriptExecutor.failed.text", "Failed script execution"},
                {"ScriptExecutor.noCommand.text", "No command registry"},
                {"ScriptExecutor.commandNotFound.text", "Command not found"},
                {"ScriptExecutor.recursionError.text", "Recursion detected"},
                {"APIConnectorFactory.connectionError.text", "Server is not available. Retry now?"},
                {"TransportTimeoutException.error", "Server communication timeout. Retry later"},
                {"APICommandMenger.connectionError.error", "Server is not available. Retry later"},
                {"WorkerTableModel.Column.ID.text", "ID"},
                {"AuthenticationApplication.userAlreadyExists.error", "User with such name already exist"},
                {"AuthenticationApplication.userAlreadyExists.error", "User with such name already exist"},
                {"AuthenticationApplication.userAlreadyExists.error", "User with such name already exist"},
                {"WorkerTableModel.Column.Owner.text", "Owner"},
                {"WorkerTableModel.Column.Creation date.text", "Creation date"},
                {"WorkerTableModel.Column.Name.text", "Name"},
                {"WorkerTableModel.Column.Salary.text", "Salary"},
                {"WorkerTableModel.Column.StartDate.text", "Start date"},
                {"WorkerTableModel.Column.EndDate.text", "End date"},
                {"WorkerTableModel.Column.XCoordinate.text", "X coordinate"},
                {"WorkerTableModel.Column.YCoordinate.text", "Y coordinate"},
                {"WorkerTableModel.Column.Organization.text", "Organization"},
                {"WorkerTableModel.Column.Position.text", "Position"},
                {"ScriptPanel.executeScriptButton.text", "Execute script"},
                {"ScriptPanel.executeScriptButton.process.text", "Execute script process..."},
                {"AddFrame.input.error.text", "Input Error"},
                {"OrgUpdateFrameZ.title", "Organization update"},
                {"OrgUpdateFrameZ.addOrgError.text", "Update organization error"},
                {"OrgUpdateFrameZ.updateError.text", "Could not update organization"},
                {"OrgAddFrameZ.title", "Organization add"},
                {"OrgAddFrameZ.addOrgError.text", "Add organization error"},
                {"MainFrame.scriptChooseText.text", "Select a script file"},
                {"MainFrame.scriptChooseText.description", "Lab Scripts (*.zb)"},
                {"MainFrame.canNotGetUsernameError.text", "Can not get username"},
                {"MainFrame.canNotGetMineWorkers.text", "Can not get username"},
                {"MainFrame.canNotFetInfo.text", "Can not get info"},
                {"MainFrame.canNotAddMsg.text", "Failed to add worker"},
                {"AbstractDateField.toolText.format", "The date must be entered using the following pattern: "},
                {"UsernameField.toolText.text", "Enter your username"},
                {"PasswordField.toolText.text", "Enter your password"},
                {"bottomPanel.infoText.text", "collection type"},
                {"bottomPanel.label1.text", "workers"},
                {"bottomPanel.label2.text", "initialization date"},
                {"bottomPanel.label4.text", "count"},
                {"loginPage.checkBox1.text", "text"},
                {"loginPage.loginButton.text", "Login"},
                {"loginPage.registerButton.text", "Register"},
                {"loginPage.title", "Login"},
                {"loginPage.label1.text", "Login"},
                {"loginPage.label2.text", "Username"},
                {"loginPage.label3.text", "Password"},
                {"command.error.invalidField", "Invalid fields"},
                {"command.error.requestFailed", "Request failed"},
                {"loginPage.error.authorizationFailed", "Authentication failed"},
                {"MainFrame.title", "lab8"},
                {"MainFrame.scriptMenuButton.text", "scripts"},
                {"MainFrame.workersMenuButton.text", "workers"},
                {"MainFrame.mapMenuButton.text", "map"},
                {"MainFrame.orgsMenuButton.text", "organizations"},
                {"OrgAddFrame.addOrgButton.text", "add"},
                {"OrgUpdateFrameZ.addOrgButton.text", "update"},
                {"OrgAddFrame.orgrAddCancelButton.text", "cancel"},
                {"OrganizationShowPanel.clearOrgButton.text", "Clear"},
                {"OrganizationShowPanel.openAddOrgPlane.text", "Add"},
                {"OrgUpdateFrame.updateOrgButton.text", "update"},
                {"OrgUpdateFrame.orgUpdateCancelButton.text", "cancel"},
                {"userInfo.workersCountText.text", "owned workers count:"},
                {"userInfo.workersCountField.text", "-2"},
                {"userInfo.username.text", "username"},
                {"userInfo.usernameField.text", "name"},
                {"WorkerAddFrame.title", "Adding workers"},
                {"WorkerAddFrame.addWorkerButton.text", "add"},
                {"WorkerAddFrame.workerAddCancelButton.text", "cancel"},
                {"WorkerAddFrame.nornalAdd.text", "Normal"},
                {"WorkerAddFrame.ifMaxAdd.text", "If max"},
                {"WorkerAddFrame.ifMainAdd.text", "if min"},
                {"WorkerAddFrame.newIdMsg", "new worker id"},
                {"OrgAddFrameZ.newIdMsg", "new organization id"},
                {"WorkerUpdateFrameZ.title", "Worker update"},
                {"WorkerUpdateFrameZ.updateWorkerButton.text", "update"},
                {"WorkerUpdateFrameZ.updateFailed.text", "update failed"},
                {"WorkerUpdateFrameZ.deleteFailed.text", "delete failed"},
                {"WorkerUpdateFrameZ.updateFailed.notOwned.text", "Object is not owned by current user"},
                {"WorkerRemoveGFrame.title", "Remove greater workers"},
                {"WorkerRemoveGFrame.removeGreaterWorkerButton.text", "remove greater"},
                {"WorkerRemoveGFrame.removeGreaterWorkerButton.error.text", "Remove greater command error"},
                {"OrganizationValidator.workerName.null.text", "name can not be null"},
                {"OrganizationValidator.label1.text", "name"},
                {"OrganizationValidator.label2.text", "type"},
                {"CredentialsValidator.username.empty", "username cannot be empty"},
                {"CredentialsValidator.invalid.text", "Credentials are invalid"},
                {"CredentialsValidator.username.invalidLength", "username must be shorter than 40 characters"},
                {"CredentialsValidator.password.invalidLength", "password must be longer than 8 characters"},
                {"WorkerInfoPanel.label1.text", "name"},
                {"WorkerInfoPanel.label2.text", "salary"},
                {"WorkerInfoPanel.label3.text", "start date"},
                {"WorkerInfoPanel.label4.text", "end date"},
                {"WorkerInfoPanel.endDateNull.text", "absent"},
                {"WorkerInfoPanel.label5.text", "coordinates x"},
                {"WorkerInfoPanel.label6.text", "coordinates y"},
                {"WorkerInfoPanel.label7.text", "organization id"},
                {"WorkerInfoPanel.label8.text", "position"},
                {"WorkerInfoPanel.workerName.null.text", "name can not be null"},
                {"WorkerInfoPanel.workerName.toolTip.text", "enter worker's name"},
                {"WorkerInfoPanel.workerSalary.null.text", "salary can not be null"},
                {"WorkerInfoPanel.workerSalary.badFormat.text", "salary must be float type"},
                {"WorkerInfoPanel.workerSalary.notInRange.text", "salary must be greater than zero"},
                {"WorkerInfoPanel.xCoordinate.null.text", "X coordinate can not be null"},
                {"WorkerInfoPanel.xCoordinate.badFormat.text", "X coordinate must be an integer"},
                {"WorkerInfoPanel.xCoordinate.notInRange.text", "X coordinate must be less than 773"},
                {"WorkerInfoPanel.yCoordinate.null.text", "Y coordinate can not be null"},
                {"WorkerInfoPanel.yCoordinate.badFormat.text", "Y coordinate must be a number"},
                {"WorkerInfoPanel.yCoordinate.notInRange.text", "Y coordinate must be greater than -413"},
                {"WorkerHeaderPanel.idLabel.text", "Worker's id"},
                {"OrganizationHeaderPanel.idLabel.text", "Organization's id"},
                {"WorkerHeaderPanel.usernameLabel.text", "Owner's username"},
                {"EnumPresenter.value.null.text", ""},
                {"EnumPresenter.value.cleaner.text", "cleaner"},
                {"EnumPresenter.value.manager_of_cleaning.text", "manager of cleaning"},
                {"EnumPresenter.value.engineer.text", "engineer"},
                {"EnumPresenter.value.lead_developer.text", "lead developer"},
                {"EnumPresenter.value.head_of_department.text", "head of department"},
                {"EnumPresenter.value.commercial.text", "commercial"},
                {"EnumPresenter.value.public.text", "public"},
                {"EnumPresenter.value.private_limited_company.text", "private limited company"},
                {"EnumPresenter.value.open_joint_stock_company.text", "open joint stock company"},
                {"WorkerShowPanel.openAddWorkerPlane.text", "Add"},
                {"WorkerShowPanel.clearWorkerButton.text", "Clear"},
                {"WorkerShowPanel.removeGreaterButton.text", "Remove greater"},
                {"WorkerShowPanel.canNotClear.text", "Can not clear"},
                {"WorkerUpdateFrame.updateWorkerButton.text", "update"},
                {"WorkerUpdateFrame.deleteWorkerButton.text", "delete"},
                {"WorkerUpdateFrame.workerUpdateCancelButton.text", "cancel"},
                {"OrganizationShowPanel.openAddWorkerPlane.text", "Add"},
                {"OrganizationIdBox.validateValue.notFound.text", "This id doesn't exist anymore"},
                {"OrganizationIdBox.previousValue.notFound.text", "Previous id doesn't exist anymore"},
                {"OrganizationIdBox.getIds.error", "Previous id doesn't exist anymore"},
                {"AddCommandResponse.getUserMessage.text", "Success with ID"},
                {"AddOrgCommandResponse.getUserMessage.text", "Success with ID"},
                {"APICommandResponse.getUserMessage.text", "Invalid credentials"},
                {"APICommandResponse.serializationError.text", "Failed to serialize"},
                {"DefaultCollectionCommandResponse.getUserMessage.text", "Collection"},
                {"ExecuteScriptCommandResponse.getUserMessage.success.text", "The execution was successful"},
                {"FilterLessPosCommandResponse.getUserMessage.text", "elements whose position field value is less than the given one"},
                {"GetSelfInfoResponse.getUserMessage.text", "Your ID: "},
                {"GetWorkerCommandResponse.getUserMessage.text", "Worker: "},
                {"InfoCommandResponse.getUserMessage.text", "Collection info:\n"},
                {"ListAPICommandResponse.getUserMessage.text", "Commands response:"},
                {"OrganisationCommandResponse.getUserMessage.text", "Organizations:\n"},
                {"UniqueOrganisationCommandResponse.getUserMessage.text", "organization field unique values:\n"},
                {"PrintDescendingCommandResponse.getUserMessage.text", "collection elements in descending order"},
                {"DBInsertApplication.addError.text", "Couldn't add"},
                {"DBInsertApplication.noElementError.text", "No elements"},
                {"DBInsertApplication.notMaximum.text", "Not maximum"},
                {"DBInsertApplication.notMinimum.text", "Not minimum"},
                {"DBReadExecutor.noWorkerById.text", "No worker with given id"},
                {"DBRemoveApplication.noEntity.text", "Not found entity to remove"},
                {"DBUpdateApplication.error.text", "Could not update entity"},
                {"OperationType.readError.text", "read failed"},
                {"OperationType.writeError.text", "write failed"},
                {"OperationType.readPermissionError.text", "no read permission for current user"},
                {"OperationType.writePermissionError.text", "no write permission for current user"},
                {"OperationType.createPermissionError.text", "no permissions to create file"},
                {"OperationType.invalidPath.text", "may be not a regular file or have invalid path"},
                {"OperationType.updatePermissionError.text", "failed to update permissions"},
                {"FileAccessException.fileAccessError.text", "Failed to access file"},
                {"FileAccessException.title.text", "file access"},
                {"Executor.skip.text", "Failed to access file"},
                {"Executor.validationError.text", "Validation failed"},
                {"ErrorHandlingApplication.methodNotAvailable", "Method not available"},
                {"OrganizationTableModel.Column.ID.text", "ID"},
                {"OrganizationTableModel.Column.Name.text", "Name"},
                {"OrganizationTableModel.Column.Type.text", "Type"},
                {"OrganizationTable.deleteError.text", "Delete failed"},
                {"ScriptLocalCommand.invalidScript.text", "Invalid Script"},
                {"ScriptLocalCommand.noScriptFile.text", "No script file passed"},
                {"ScriptInvoker.default.success", "Success!"},
                {"ScriptInvoker.scriptFailed.text", "Script failed: "},
                {"ScriptInvoker.scriptFailed.unknownError.text", "Unknown error"},
                {"ScriptInvoker.help.text", "God will help"}
        };
    }
}