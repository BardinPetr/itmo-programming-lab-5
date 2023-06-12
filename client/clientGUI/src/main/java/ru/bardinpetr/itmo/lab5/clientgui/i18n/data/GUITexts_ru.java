package ru.bardinpetr.itmo.lab5.clientgui.i18n.data;

import java.util.ListResourceBundle;

public class GUITexts_ru extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"dateFormat", "dd.MM.yyyy"},
                {"optionalAnswers.Yes", "��"},
                {"optionalAnswers.No", "���"},
                {"optionalAnswers.Ok", "������"},
                {"generalError", "���-�� ����� �� ��� � ������� ������� ��������"},
                {"ScriptExecutor.recursionError.text", "���������� ��������"},
                {"APIConnectorFactory.connectionError.text", "������ ����������. ��������� ������� ������?"},
                {"TransportTimeoutException.error", "������� ����� �������� ����� � ��������. ��������� ������� �����"},
                {"APICommandMenger.connectionError.error", "������ ����������. ��������� ������� �����"},
                {"WorkerTableModel.Column.ID.text", "�������������"},
                {"AuthenticationApplication.userAlreadyExists.error", "������������ � ����� ������ ��� ����������"},
                {"WorkerTableModel.Column.Owner.text", "��������"},
                {"WorkerTableModel.Column.Creation date.text", "���� ��������"},
                {"WorkerTableModel.Column.Name.text", "���"},
                {"WorkerTableModel.Column.Salary.text", "��������"},
                {"WorkerTableModel.Column.StartDate.text", "���� ������"},
                {"WorkerTableModel.Column.EndDate.text", "���� ���������"},
                {"WorkerTableModel.Column.XCoordinate.text", "���������� X"},
                {"WorkerTableModel.Column.YCoordinate.text", "���������� Y"},
                {"WorkerTableModel.Column.Organization.text", "�����������"},
                {"WorkerTableModel.Column.Position.text", "�������"},
                {"ScriptPanel.executeScriptButton.text", "���������� �������"},
                {"AddFrame.input.error.text", "������ �����"},
                {"OrgAddFrameZ.title", "���������� �����������"},
                {"OrgAddFrameZ.addOrgError.text", "�������� ������ �����������"},
                {"MainFrame.scriptChooseText.text", "�������� ���� ��������"},
                {"MainFrame.scriptChooseText.description", "������������ �������� (*.zb)"},
                {"MainFrame.canNotGetUsernameError.text", "�� ������� �������� ��� ������������"},
                {"MainFrame.canNotGetMineWorkers.text", "�� ������� �������� ��� ������������"},
                {"MainFrame.canNotFetInfo.text", "�� ���� �������� ����������"},
                {"MainFrame.canNotAddMsg.text", "�� ������� �������� �������"},
                {"AbstractDateField.toolText.format", "���� ������ ���� ������� �� ���������� �������:"},
                {"UsernameField.toolText.text", "������� ���� ��� ������������"},
                {"PasswordField.toolText.text", "������� ���� ������"},
                {"bottomPanel.infoText.text", "��� ���������"},
                {"bottomPanel.label1.text", "����������"},
                {"bottomPanel.label2.text", "���� �������������"},
                {"bottomPanel.label4.text", "�������"},
                {"loginPage.checkBox1.text", "���"},
                {"loginPage.loginButton.text", "�����"},
                {"loginPage.registerButton.text", "��������������"},
                {"loginPage.title", "�����"},
                {"loginPage.label1.text", "�����"},
                {"loginPage.label2.text", "��� ������������"},
                {"loginPage.label3.text", "������"},
                {"command.error.invalidField", "������������ ����"},
                {"command.error.requestFailed", "���� �������"},
                {"loginPage.error.authorizationFailed", "������ ��������������"},
                {"MainFrame.title", "�����������8"},
                {"MainFrame.scriptMenuButton.text", "��������"},
                {"MainFrame.workersMenuButton.text", "����������"},
                {"MainFrame.mapMenuButton.text", "�����"},
                {"MainFrame.orgsMenuButton.text", "�����������"},
                {"OrgAddFrame.addOrgButton.text", "���������"},
                {"OrgAddFrame.orgrAddCancelButton.text", "������"},
                {"OrganizationShowPanel.clearOrgButton.text", "�����"},
                {"OrganizationShowPanel.openAddOrgPlane.text", "���������"},
                {"OrgUpdateFrame.updateOrgButton.text", "���������"},
                {"OrgUpdateFrame.orgUpdateCancelButton.text", "������"},
                {"userInfo.workersCountText.text", "���������� ����������, ������������� �������������:"},
                {"userInfo.workersCountField.text", "-2"},
                {"userInfo.username.text", "��� ������������"},
                {"userInfo.usernameField.text", "���"},
                {"WorkerAddFrame.title", "���������� ��������"},
                {"WorkerAddFrame.addWorkerButton.text", "���������"},
                {"WorkerAddFrame.workerAddCancelButton.text", "������"},
                {"WorkerAddFrame.nornalAdd.text", "����������"},
                {"WorkerAddFrame.ifMaxAdd.text", "���� ����"},
                {"WorkerAddFrame.ifMainAdd.text", "���� ���."},
                {"WorkerAddFrame.newIdMsg", "����� ������������� ���������"},
                {"OrgAddFrameZ.newIdMsg", "����� ������������� �����������"},
                {"WorkerUpdateFrameZ.title", "���������� �������"},
                {"WorkerUpdateFrameZ.updateWorkerButton.text", "���������"},
                {"WorkerUpdateFrameZ.updateFailed.text", "���� ����������"},
                {"WorkerUpdateFrameZ.deleteFailed.text", "�� ������� �������"},
                {"WorkerUpdateFrameZ.updateFailed.notOwned.text", "������ �� ����������� �������� ������������"},
                {"WorkerRemoveGFrame.title", "�������� ����� ������� ����������"},
                {"WorkerRemoveGFrame.removeGreaterWorkerButton.text", "������� ������"},
                {"WorkerRemoveGFrame.removeGreaterWorkerButton.error.text", "������� ������� ������ �������"},
                {"OrganizationValidator.workerName.null.text", "name �� ����� ���� �������"},
                {"OrganizationValidator.label1.text", "���"},
                {"OrganizationValidator.label2.text", "���"},
                {"CredentialsValidator.username.empty", "��� ������������ �� ����� ���� ������"},
                {"CredentialsValidator.invalid.text", "������� ������ ���������������"},
                {"CredentialsValidator.username.invalidLength", "��� ������������ ������ ���� ������ 40 ��������"},
                {"CredentialsValidator.password.invalidLength", "������ ������ ���� ������� 8 ��������"},
                {"WorkerInfoPanel.label1.text", "���"},
                {"WorkerInfoPanel.label2.text", "��������"},
                {"WorkerInfoPanel.label3.text", "���� ������"},
                {"WorkerInfoPanel.label4.text", "���� ���������"},
                {"WorkerInfoPanel.endDateNull.text", "�������������"},
                {"WorkerInfoPanel.label5.text", "���������� x"},
                {"WorkerInfoPanel.label6.text", "���������� Y"},
                {"WorkerInfoPanel.label7.text", "������������� �����������"},
                {"WorkerInfoPanel.label8.text", "�������"},
                {"WorkerInfoPanel.workerName.null.text", "name �� ����� ���� �������"},
                {"WorkerInfoPanel.workerName.toolTip.text", "������� ��� ���������"},
                {"WorkerInfoPanel.workerSalary.null.text", "�������� �� ����� ���� �������"},
                {"WorkerInfoPanel.workerSalary.badFormat.text", "�������� ������ ���� ���������� ����"},
                {"WorkerInfoPanel.workerSalary.notInRange.text", "�������� ������ ���� ������ ����"},
                {"WorkerInfoPanel.xCoordinate.null.text", "���������� X �� ����� ���� �������"},
                {"WorkerInfoPanel.xCoordinate.badFormat.text", "���������� X ������ ���� ����� ������"},
                {"WorkerInfoPanel.xCoordinate.notInRange.text", "���������� X ������ ���� ������ 773"},
                {"WorkerInfoPanel.yCoordinate.null.text", "���������� Y �� ����� ���� �������"},
                {"WorkerInfoPanel.yCoordinate.badFormat.text", "���������� Y ������ ���� ������"},
                {"WorkerInfoPanel.yCoordinate.notInRange.text", "���������� Y ������ ���� ������ -413"},
                {"WorkerHeaderPanel.idLabel.text", "������������� ���������"},
                {"WorkerHeaderPanel.usernameLabel.text", "��� ������������ ���������"},
                {"EnumPresenter.value.null.text", ""},
                {"EnumPresenter.value.cleaner.text", "�������"},
                {"EnumPresenter.value.manager_of_cleaning.text", "�������� �� ������"},
                {"EnumPresenter.value.engineer.text", "�������"},
                {"EnumPresenter.value.lead_developer.text", "������� �����������"},
                {"EnumPresenter.value.head_of_department.text", "���������� ��������"},
                {"EnumPresenter.value.commercial.text", "��������"},
                {"EnumPresenter.value.public.text", "������������"},
                {"EnumPresenter.value.private_limited_company.text", "������� �������� � ������������ ����������������"},
                {"EnumPresenter.value.open_joint_stock_company.text", "�������� ����������� ��������"},
                {"WorkerShowPanel.openAddWorkerPlane.text", "���������"},
                {"WorkerShowPanel.clearWorkerButton.text", "�����"},
                {"WorkerShowPanel.removeGreaterButton.text", "������� ������"},
                {"WorkerShowPanel.canNotClear.text", "�� ���� ������"},
                {"WorkerUpdateFrame.updateWorkerButton.text", "���������"},
                {"WorkerUpdateFrame.deleteWorkerButton.text", "�������"},
                {"WorkerUpdateFrame.workerUpdateCancelButton.text", "������"},
                {"OrganizationShowPanel.openAddWorkerPlane.text", "���������"},
                {"OrganizationIdBox.validateValue.notFound.text", "���� ������������� ������ �� ����������"},
                {"OrganizationIdBox.previousValue.notFound.text", "���������� ������������� ������ �� ����������"},
                {"OrganizationIdBox.getIds.error", "���������� ������������� ������ �� ����������"},
                {"AddCommandResponse.getUserMessage.text", "����� � ID"},
                {"AddOrgCommandResponse.getUserMessage.text", "����� � ID"},
                {"APICommandResponse.getUserMessage.text", "�������� ������� ������"},
                {"APICommandResponse.serializationError.text", "�� ������� �������������"},
                {"DefaultCollectionCommandResponse.getUserMessage.text", "���������"},
                {"ExecuteScriptCommandResponse.getUserMessage.success.text", "����� ������ �������"},
                {"FilterLessPosCommandResponse.getUserMessage.text", "��������, �������� ���� ������� ������� ������ ���������"},
                {"GetSelfInfoResponse.getUserMessage.text", "���� ������������� ��������:"},
                {"GetWorkerCommandResponse.getUserMessage.text", "�������:"},
                {"InfoCommandResponse.getUserMessage.text", "���������� � ���������:\n"},
                {"ListAPICommandResponse.getUserMessage.text", "����� ������:"},
                {"OrganisationCommandResponse.getUserMessage.text", "�����������:\n"},
                {"UniqueOrganisationCommandResponse.getUserMessage.text", "���������� �������� ���� �����������:\n"},
                {"PrintDescendingCommandResponse.getUserMessage.text", "�������� ��������� � ������� ��������"},
                {"DBInsertApplication.addError.text", "�� ������� ��������"},
                {"DBInsertApplication.noElementError.text", "��� ���������"},
                {"DBInsertApplication.notMaximum.text", "�� ��������"},
                {"DBInsertApplication.notMinimum.text", "�� �����������"},
                {"DBReadExecutor.noWorkerById.text", "��� ��������� � ������ ���������������"},
                {"DBRemoveApplication.noEntity.text", "�� ��������� �������� ��� ��������"},
                {"OperationType.readError.text", "������ ������"},
                {"OperationType.writeError.text", "���� ������"},
                {"OperationType.readPermissionError.text", "��� ���������� �� ������ ��� �������� ������������"},
                {"OperationType.writePermissionError.text", "��� ���������� �� ������ ��� �������� ������������"},
                {"OperationType.createPermissionError.text", "��� ���������� �� �������� �����"},
                {"OperationType.invalidPath.text", "��������, ��� �� ������� ���� ��� ������������ ����"},
                {"OperationType.updatePermissionError.text", "�� ������� �������� ����������"},
                {"FileAccessException.fileAccessError.text", "�� ������� �������� ������ � �����"},
                {"FileAccessException.title.text", "������ � ������"},
                {"Executor.skip.text", "�� ������� �������� ������ � �����"},
                {"Executor.validationError.text", "������ ��������"},
                {"ErrorHandlingApplication.methodNotAvailable", "����� ����������"},
                {"OrganizationTableModel.Column.ID.text", "�������������"},
                {"OrganizationTableModel.Column.Name.text", "���"},
                {"OrganizationTableModel.Column.Type.text", "���"}
        };
    }
}