package ru.bardinpetr.itmo.lab5.clientgui.i18n.data;

import java.util.ListResourceBundle;

public class GUITexts_ru extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"dateFormat", "дд.мм.гггг"},
                {"generalError", "Что-то пошло не так с помощью команды отправки"},
                {"WorkerTableModel.Column.ID.text", "ИДЕНТИФИКАТОР"},
                {"WorkerTableModel.Column.Owner.text", "Владелец"},
                {"WorkerTableModel.Column.Creation date.text", "Дата создания"},
                {"WorkerTableModel.Column.Name.text", "Имя"},
                {"WorkerTableModel.Column.Salary.text", "Зарплата"},
                {"WorkerTableModel.Column.StartDate.text", "Дата начала"},
                {"WorkerTableModel.Column.EndDate.text", "Дата окончания"},
                {"WorkerTableModel.Column.XCoordinate.text", "Координата X"},
                {"WorkerTableModel.Column.YCoordinate.text", "Координата Y"},
                {"WorkerTableModel.Column.Organization.text", "Организация"},
                {"WorkerTableModel.Column.Position.text", "Позиция"},
                {"ScriptPanel.executeScriptButton.text", "Выполнение скрипта"},
                {"AddFrame.input.error.text", "Ошибка ввода"},
                {"OrgAddFrameZ.title", "Добавление организации"},
                {"OrgAddFrameZ.addOrgError.text", "Добавить ошибку организации"},
                {"MainFrame.scriptChooseText.text", "Выберите файл сценария"},
                {"MainFrame.scriptChooseText.description", "Лабораторные сценарии (*.zb)"},
                {"MainFrame.canNotGetUsernameError.text", "Не удается получить имя пользователя"},
                {"MainFrame.canNotGetMineWorkers.text", "Не удается получить имя пользователя"},
                {"MainFrame.canNotFetInfo.text", "Не могу получить информацию"},
                {"MainFrame.canNotAddMsg.text", "Не удалось добавить воркера"},
                {"AbstractDateField.toolText.format", "Дата должна быть введена по следующему шаблону:"},
                {"UsernameField.toolText.text", "Введите свое имя пользователя"},
                {"PasswordField.toolText.text", "Введите свой пароль"},
                {"bottomPanel.infoText.text", "Тип коллекции"},
                {"bottomPanel.label1.text", "Работников"},
                {"bottomPanel.label2.text", "Дата инициализации"},
                {"bottomPanel.label4.text", "считать"},
                {"loginPage.checkBox1.text", "СМС"},
                {"loginPage.loginButton.text", "Логин"},
                {"loginPage.registerButton.text", "Регистрировать"},
                {"loginPage.title", "Логин"},
                {"loginPage.label1.text", "Логин"},
                {"loginPage.label2.text", "Имя пользователя"},
                {"loginPage.label3.text", "Пароль"},
                {"command.error.invalidField", "Недопустимые поля"},
                {"command.error.requestFailed", "Сбой запроса"},
                {"loginPage.error.authorizationFailed", "Ошибка аутентификации"},
                {"MainFrame.title", "Лаборатория8"},
                {"MainFrame.scriptMenuButton.text", "Сценарии"},
                {"MainFrame.workersMenuButton.text", "Работников"},
                {"MainFrame.mapMenuButton.text", "карта"},
                {"MainFrame.orgsMenuButton.text", "Организаций"},
                {"OrgAddFrame.addOrgButton.text", "добавлять"},
                {"OrgAddFrame.orgrAddCancelButton.text", "Отмена"},
                {"OrganizationShowPanel.clearOrgButton.text", "Ясный"},
                {"OrganizationShowPanel.openAddOrgPlane.text", "Добавлять"},
                {"OrgUpdateFrame.updateOrgButton.text", "обновлять"},
                {"OrgUpdateFrame.orgUpdateCancelButton.text", "Отмена"},
                {"userInfo.workersCountText.text", "Количество работников, принадлежащих собственникам:"},
                {"userInfo.workersCountField.text", "-2"},
                {"userInfo.username.text", "Имя пользователя"},
                {"userInfo.usernameField.text", "имя"},
                {"WorkerAddFrame.title", "Добавление воркеров"},
                {"WorkerAddFrame.addWorkerButton.text", "добавлять"},
                {"WorkerAddFrame.workerAddCancelButton.text", "Отмена"},
                {"WorkerAddFrame.nornalAdd.text", "Нормальный"},
                {"WorkerAddFrame.ifMaxAdd.text", "Если макс"},
                {"WorkerAddFrame.ifMainAdd.text", "Если мин."},
                {"WorkerAddFrame.newIdMsg", "Новый идентификатор работника"},
                {"OrgAddFrameZ.newIdMsg", "Новый идентификатор организации"},
                {"WorkerUpdateFrameZ.title", "Обновление воркера"},
                {"WorkerUpdateFrameZ.updateWorkerButton.text", "обновлять"},
                {"WorkerUpdateFrameZ.updateFailed.text", "Сбой обновления"},
                {"WorkerUpdateFrameZ.deleteFailed.text", "Не удалось удалить"},
                {"WorkerUpdateFrameZ.updateFailed.notOwned.text", "Объект не принадлежит текущему пользователю"},
                {"WorkerRemoveGFrame.title", "Удаление более крупных работников"},
                {"WorkerRemoveGFrame.removeGreaterWorkerButton.text", "Удалить больше"},
                {"WorkerRemoveGFrame.removeGreaterWorkerButton.error.text", "Удалить большую ошибку команды"},
                {"OrganizationValidator.workerName.null.text", "name не может быть нулевым"},
                {"OrganizationValidator.label1.text", "имя"},
                {"OrganizationValidator.label2.text", "Тип"},
                {"CredentialsValidator.username.empty", "Имя пользователя не может быть пустым"},
                {"CredentialsValidator.invalid.text", "Учетные данные недействительны"},
                {"CredentialsValidator.username.invalidLength", "Имя пользователя должно быть короче 40 символов"},
                {"CredentialsValidator.password.invalidLength", "пароль должен быть длиннее 8 символов"},
                {"WorkerInfoPanel.label1.text", "имя"},
                {"WorkerInfoPanel.label2.text", "зарплата"},
                {"WorkerInfoPanel.label3.text", "Дата начала"},
                {"WorkerInfoPanel.label4.text", "Дата окончания"},
                {"WorkerInfoPanel.endDateNull.text", "недействительный"},
                {"WorkerInfoPanel.label5.text", "Координаты x"},
                {"WorkerInfoPanel.label6.text", "Координаты Y"},
                {"WorkerInfoPanel.label7.text", "Идентификатор организации"},
                {"WorkerInfoPanel.label8.text", "позиция"},
                {"WorkerInfoPanel.workerName.null.text", "name не может быть нулевым"},
                {"WorkerInfoPanel.workerName.toolTip.text", "Введите имя работника"},
                {"WorkerInfoPanel.workerSalary.null.text", "зарплата не может быть нулевой"},
                {"WorkerInfoPanel.workerSalary.badFormat.text", "Зарплата должна быть плавающего типа"},
                {"WorkerInfoPanel.workerSalary.notInRange.text", "Зарплата должна быть больше нуля"},
                {"WorkerInfoPanel.xCoordinate.null.text", "Координата X не может быть нулевой"},
                {"WorkerInfoPanel.xCoordinate.badFormat.text", "Координата X должна быть целым числом"},
                {"WorkerInfoPanel.xCoordinate.notInRange.text", "Координата X должна быть меньше 773"},
                {"WorkerInfoPanel.yCoordinate.null.text", "Координата Y не может быть нулевой"},
                {"WorkerInfoPanel.yCoordinate.badFormat.text", "Координата Y должна быть числом"},
                {"WorkerInfoPanel.yCoordinate.notInRange.text", "Координата Y должна быть больше -413"},
                {"WorkerHeaderPanel.idLabel.text", "Идентификатор работника"},
                {"WorkerHeaderPanel.usernameLabel.text", "Имя пользователя владельца"},
                {"EnumPresenter.value.null.text", ""},
                {"EnumPresenter.value.cleaner.text", "уборщик"},
                {"EnumPresenter.value.manager_of_cleaning.text", "Менеджер по уборке"},
                {"EnumPresenter.value.engineer.text", "инженер"},
                {"EnumPresenter.value.lead_developer.text", "Ведущий разработчик"},
                {"EnumPresenter.value.head_of_department.text", "Заведующий кафедрой"},
                {"EnumPresenter.value.commercial.text", "торговый"},
                {"EnumPresenter.value.public.text", "общественный"},
                {"EnumPresenter.value.private_limited_company.text", "Частная компания с ограниченной ответственностью"},
                {"EnumPresenter.value.open_joint_stock_company.text", "Открытое акционерное общество"},
                {"WorkerShowPanel.openAddWorkerPlane.text", "Добавлять"},
                {"WorkerShowPanel.clearWorkerButton.text", "Ясный"},
                {"WorkerShowPanel.removeGreaterButton.text", "Удалить больше"},
                {"WorkerShowPanel.canNotClear.text", "Не могу понять"},
                {"WorkerUpdateFrame.updateWorkerButton.text", "обновлять"},
                {"WorkerUpdateFrame.deleteWorkerButton.text", "удалить"},
                {"WorkerUpdateFrame.workerUpdateCancelButton.text", "Отмена"},
                {"OrganizationShowPanel.openAddWorkerPlane.text", "Добавлять"},
                {"OrganizationIdBox.validateValue.notFound.text", "Этот идентификатор больше не существует"},
                {"OrganizationIdBox.previousValue.notFound.text", "Предыдущий идентификатор больше не существует"},
                {"OrganizationIdBox.getIds.error", "Предыдущий идентификатор больше не существует"},
                {"AddCommandResponse.getUserMessage.text", "Успех с ID"},
                {"AddOrgCommandResponse.getUserMessage.text", "Успех с ID"},
                {"APICommandResponse.getUserMessage.text", "Неверные учетные данные"},
                {"APICommandResponse.serializationError.text", "Не удалось сериализовать"},
                {"DefaultCollectionCommandResponse.getUserMessage.text", "Коллекция"},
                {"ExecuteScriptCommandResponse.getUserMessage.success.text", "Казнь прошла успешно"},
                {"FilterLessPosCommandResponse.getUserMessage.text", "элементы, значение поля позиции которых меньше заданного"},
                {"GetSelfInfoResponse.getUserMessage.text", "Ваше удостоверение личности:"},
                {"GetWorkerCommandResponse.getUserMessage.text", "Рабочий:"},
                {"InfoCommandResponse.getUserMessage.text", "Информация о коллекции:\n"},
                {"ListAPICommandResponse.getUserMessage.text", "Ответ команд:"},
                {"OrganisationCommandResponse.getUserMessage.text", "Организации:\n"},
                {"UniqueOrganisationCommandResponse.getUserMessage.text", "Уникальные значения поля организации:\n"},
                {"PrintDescendingCommandResponse.getUserMessage.text", "Элементы коллекции в порядке убывания"},
                {"DBInsertApplication.addError.text", "Не удалось добавить"},
                {"DBInsertApplication.noElementError.text", "Нет элементов"},
                {"DBInsertApplication.notMaximum.text", "Не максимум"},
                {"DBInsertApplication.notMinimum.text", "Не минимальный"},
                {"DBReadExecutor.noWorkerById.text", "Нет работника с данным идентификатором"},
                {"DBRemoveApplication.noEntity.text", "Не найденная сущность для удаления"},
                {"OperationType.readError.text", "Ошибка чтения"},
                {"OperationType.writeError.text", "Сбой записи"},
                {"OperationType.readPermissionError.text", "Нет разрешения на чтение для текущего пользователя"},
                {"OperationType.writePermissionError.text", "Нет разрешения на запись для текущего пользователя"},
                {"OperationType.createPermissionError.text", "Нет разрешений на создание файла"},
                {"OperationType.invalidPath.text", "Возможно, это не обычный файл или недопустимый путь"},
                {"OperationType.updatePermissionError.text", "Не удалось обновить разрешения"},
                {"FileAccessException.fileAccessError.text", "Не удалось получить доступ к файлу"},
                {"FileAccessException.title.text", "Доступ к файлам"},
                {"Executor.skip.text", "Не удалось получить доступ к файлу"},
                {"Executor.validationError.text", "Ошибка проверки"},
                {"ErrorHandlingApplication.methodNotAvailable", "Метод недоступен"}
        };
    }
}