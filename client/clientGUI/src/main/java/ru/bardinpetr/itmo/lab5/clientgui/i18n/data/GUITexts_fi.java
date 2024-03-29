package ru.bardinpetr.itmo.lab5.clientgui.i18n.data;

import java.util.ListResourceBundle;

public class GUITexts_fi extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"dateFormat", "dd.MM.yyyy"},
                {"optionalAnswers.Yes", "Kyllä"},
                {"optionalAnswers.No", "Ei"},
                {"optionalAnswers.Ok", "OKEI"},
                {"generalError", "Jokin meni pieleen komennon lähettämisessä"},
                {"ScriptExecutor.failed.text", "Komentosarjan suorittaminen epäonnistui"},
                {"ScriptExecutor.noCommand.text", "Ei komentorekisteriä"},
                {"ScriptExecutor.commandNotFound.text", "Komentoa ei löydy"},
                {"ScriptExecutor.recursionError.text", "Rekursio havaittu"},
                {"APIConnectorFactory.connectionError.text", "Palvelin ei ole käytettävissä. Yritä uudelleen nyt?"},
                {"TransportTimeoutException.error", "Palvelinyhteyden aikakatkaisu. Yritä myöhemmin uudelleen"},
                {"APICommandMenger.connectionError.error", "Palvelin ei ole käytettävissä. Yritä myöhemmin uudelleen"},
                {"WorkerTableModel.Column.ID.text", "HENKILÖLLISYYSTODISTUS"},
                {"AuthenticationApplication.userAlreadyExists.error", "Käyttäjä, jolla on tällainen nimi, on jo olemassa"},
                {"WorkerTableModel.Column.Owner.text", "Omistaja"},
                {"WorkerTableModel.Column.Creation date.text", "Luomisen päivämäärä"},
                {"WorkerTableModel.Column.Name.text", "Nimi"},
                {"WorkerTableModel.Column.Salary.text", "Palkka"},
                {"WorkerTableModel.Column.StartDate.text", "Aloituspäivä"},
                {"WorkerTableModel.Column.EndDate.text", "Päättymispäivä"},
                {"WorkerTableModel.Column.XCoordinate.text", "X-koordinaatti"},
                {"WorkerTableModel.Column.YCoordinate.text", "Y-koordinaatti"},
                {"WorkerTableModel.Column.Organization.text", "Organisaatio"},
                {"WorkerTableModel.Column.Position.text", "Asema"},
                {"ScriptPanel.executeScriptButton.text", "Suorita komentosarja"},
                {"ScriptPanel.executeScriptButton.process.text", "Suorita komentosarjaprosessi..."},
                {"AddFrame.input.error.text", "Syöttövirhe"},
                {"OrgUpdateFrameZ.title", "Organisaation päivitys"},
                {"OrgUpdateFrameZ.addOrgError.text", "Päivitä organisaation virhe"},
                {"OrgUpdateFrameZ.updateError.text", "Organisaatiota ei voitu päivittää"},
                {"OrgAddFrameZ.title", "Organisaation lisäys"},
                {"OrgAddFrameZ.addOrgError.text", "Lisää organisaatiovirhe"},
                {"MainFrame.scriptChooseText.text", "Valitse komentosarjatiedosto"},
                {"MainFrame.scriptChooseText.description", "Lab-skriptit (*.zb)"},
                {"MainFrame.canNotGetUsernameError.text", "Käyttäjätunnusta ei voi saada"},
                {"MainFrame.canNotGetMineWorkers.text", "Käyttäjätunnusta ei voi saada"},
                {"MainFrame.canNotFetInfo.text", "Ei voi saada tietoa"},
                {"MainFrame.canNotAddMsg.text", "Työntekijän lisääminen epäonnistui"},
                {"AbstractDateField.toolText.format", "Päivämäärä on syötettävä seuraavalla kaavalla:"},
                {"UsernameField.toolText.text", "Anna käyttäjänimesi"},
                {"PasswordField.toolText.text", "Anna salasanasi"},
                {"bottomPanel.infoText.text", "Kokoelman tyyppi"},
                {"bottomPanel.label1.text", "Työntekijöiden"},
                {"bottomPanel.label2.text", "Alustuspäivämäärä"},
                {"bottomPanel.label4.text", "kreivi"},
                {"loginPage.checkBox1.text", "Tekstiviesti"},
                {"loginPage.loginButton.text", "Sisäänkirjautuminen"},
                {"loginPage.registerButton.text", "Rekisteröidy"},
                {"loginPage.title", "Sisäänkirjautuminen"},
                {"loginPage.label1.text", "Sisäänkirjautuminen"},
                {"loginPage.label2.text", "Käyttäjänimi"},
                {"loginPage.label3.text", "Salasana"},
                {"command.error.invalidField", "Virheelliset kentät"},
                {"command.error.requestFailed", "Pyyntö epäonnistui"},
                {"loginPage.error.authorizationFailed", "Todennus epäonnistui"},
                {"MainFrame.title", "Lab8"},
                {"MainFrame.scriptMenuButton.text", "Skriptit"},
                {"MainFrame.workersMenuButton.text", "Työntekijöiden"},
                {"MainFrame.mapMenuButton.text", "kartta"},
                {"MainFrame.orgsMenuButton.text", "Järjestöt"},
                {"OrgAddFrame.addOrgButton.text", "lisätä"},
                {"OrgUpdateFrameZ.addOrgButton.text", "päivittää"},
                {"OrgAddFrame.orgrAddCancelButton.text", "perua"},
                {"OrganizationShowPanel.clearOrgButton.text", "Selvä"},
                {"OrganizationShowPanel.openAddOrgPlane.text", "Lisätä"},
                {"OrgUpdateFrame.updateOrgButton.text", "päivittää"},
                {"OrgUpdateFrame.orgUpdateCancelButton.text", "perua"},
                {"userInfo.workersCountText.text", "Omistetut työntekijät lasketaan:"},
                {"userInfo.workersCountField.text", "-2"},
                {"userInfo.username.text", "käyttäjänimi"},
                {"userInfo.usernameField.text", "Nimi"},
                {"WorkerAddFrame.title", "Työntekijöiden lisääminen"},
                {"WorkerAddFrame.addWorkerButton.text", "lisätä"},
                {"WorkerAddFrame.workerAddCancelButton.text", "perua"},
                {"WorkerAddFrame.nornalAdd.text", "Normaali"},
                {"WorkerAddFrame.ifMaxAdd.text", "Jos max"},
                {"WorkerAddFrame.ifMainAdd.text", "jos min"},
                {"WorkerAddFrame.newIdMsg", "Uuden työntekijän tunnus"},
                {"OrgAddFrameZ.newIdMsg", "Uusi organisaatiotunnus"},
                {"WorkerUpdateFrameZ.title", "Työntekijän päivitys"},
                {"WorkerUpdateFrameZ.updateWorkerButton.text", "päivittää"},
                {"WorkerUpdateFrameZ.updateFailed.text", "päivitys epäonnistui"},
                {"WorkerUpdateFrameZ.deleteFailed.text", "Poisto epäonnistui"},
                {"WorkerUpdateFrameZ.updateFailed.notOwned.text", "Objekti ei ole nykyisen käyttäjän omistuksessa"},
                {"WorkerRemoveGFrame.title", "Poista suurempia työntekijöitä"},
                {"WorkerRemoveGFrame.removeGreaterWorkerButton.text", "poista suurempi"},
                {"WorkerRemoveGFrame.removeGreaterWorkerButton.error.text", "Poista suurempi komentovirhe"},
                {"OrganizationValidator.workerName.null.text", "nimi ei voi olla tyhjä"},
                {"OrganizationValidator.label1.text", "Nimi"},
                {"OrganizationValidator.label2.text", "tyyppi"},
                {"CredentialsValidator.username.empty", "Käyttäjänimi ei voi olla tyhjä"},
                {"CredentialsValidator.invalid.text", "Tunnistetiedot ovat virheelliset"},
                {"CredentialsValidator.username.invalidLength", "Käyttäjänimen on oltava alle 40 merkkiä pitkä"},
                {"CredentialsValidator.password.invalidLength", "salasanan on oltava pidempi kuin 8 merkkiä"},
                {"WorkerInfoPanel.label1.text", "Nimi"},
                {"WorkerInfoPanel.label2.text", "palkka"},
                {"WorkerInfoPanel.label3.text", "Aloituspäivä"},
                {"WorkerInfoPanel.label4.text", "Päättymispäivä"},
                {"WorkerInfoPanel.endDateNull.text", "poissaoleva"},
                {"WorkerInfoPanel.label5.text", "Koordinaatit x"},
                {"WorkerInfoPanel.label6.text", "Koordinaatit y"},
                {"WorkerInfoPanel.label7.text", "Organisaation tunnus"},
                {"WorkerInfoPanel.label8.text", "asema"},
                {"WorkerInfoPanel.workerName.null.text", "nimi ei voi olla tyhjä"},
                {"WorkerInfoPanel.workerName.toolTip.text", "Kirjoita työntekijän nimi"},
                {"WorkerInfoPanel.workerSalary.null.text", "Palkka ei voi olla tyhjä"},
                {"WorkerInfoPanel.workerSalary.badFormat.text", "Palkan on oltava float-tyyppinen"},
                {"WorkerInfoPanel.workerSalary.notInRange.text", "palkan on oltava suurempi kuin nolla"},
                {"WorkerInfoPanel.xCoordinate.null.text", "X-koordinaatti ei voi olla tyhjä"},
                {"WorkerInfoPanel.xCoordinate.badFormat.text", "X-koordinaatin on oltava kokonaisluku"},
                {"WorkerInfoPanel.xCoordinate.notInRange.text", "X-koordinaatin on oltava pienempi kuin 773"},
                {"WorkerInfoPanel.yCoordinate.null.text", "Y-koordinaatti ei voi olla tyhjä"},
                {"WorkerInfoPanel.yCoordinate.badFormat.text", "Y-koordinaatin on oltava luku"},
                {"WorkerInfoPanel.yCoordinate.notInRange.text", "Y-koordinaatin on oltava suurempi kuin -413"},
                {"WorkerHeaderPanel.idLabel.text", "Työntekijän tunnus"},
                {"OrganizationHeaderPanel.idLabel.text", "Organisaation tunnus"},
                {"WorkerHeaderPanel.usernameLabel.text", "Omistajan käyttäjätunnus"},
                {"EnumPresenter.value.null.text", ""},
                {"EnumPresenter.value.cleaner.text", "siivooja"},
                {"EnumPresenter.value.manager_of_cleaning.text", "siivouspäällikkö"},
                {"EnumPresenter.value.engineer.text", "insinööri"},
                {"EnumPresenter.value.lead_developer.text", "Johtava kehittäjä"},
                {"EnumPresenter.value.head_of_department.text", "osastopäällikkö"},
                {"EnumPresenter.value.commercial.text", "kaupallinen"},
                {"EnumPresenter.value.public.text", "julkinen"},
                {"EnumPresenter.value.private_limited_company.text", "Yksityinen osakeyhtiö"},
                {"EnumPresenter.value.open_joint_stock_company.text", "Avaa osakeyhtiö"},
                {"WorkerShowPanel.openAddWorkerPlane.text", "Lisätä"},
                {"WorkerShowPanel.clearWorkerButton.text", "Selvä"},
                {"WorkerShowPanel.removeGreaterButton.text", "Poista suurempi"},
                {"WorkerShowPanel.canNotClear.text", "Ei voi tyhjentää"},
                {"WorkerUpdateFrame.updateWorkerButton.text", "päivittää"},
                {"WorkerUpdateFrame.deleteWorkerButton.text", "poistaa"},
                {"WorkerUpdateFrame.workerUpdateCancelButton.text", "perua"},
                {"OrganizationShowPanel.openAddWorkerPlane.text", "Lisätä"},
                {"OrganizationIdBox.validateValue.notFound.text", "Tätä tunnusta ei ole enää olemassa"},
                {"OrganizationIdBox.previousValue.notFound.text", "Aiempaa tunnusta ei ole enää olemassa"},
                {"OrganizationIdBox.getIds.error", "Aiempaa tunnusta ei ole enää olemassa"},
                {"AddCommandResponse.getUserMessage.text", "Menestystä ID:n avulla"},
                {"AddOrgCommandResponse.getUserMessage.text", "Menestystä ID:n avulla"},
                {"APICommandResponse.getUserMessage.text", "Virheelliset tunnistetiedot"},
                {"APICommandResponse.serializationError.text", "Sarjoittaminen epäonnistui"},
                {"DefaultCollectionCommandResponse.getUserMessage.text", "Kokoelma"},
                {"ExecuteScriptCommandResponse.getUserMessage.success.text", "Toteutus onnistui"},
                {"FilterLessPosCommandResponse.getUserMessage.text", "elementit, joiden sijaintikentän arvo on pienempi kuin annettu"},
                {"GetSelfInfoResponse.getUserMessage.text", "Henkilötodistuksesi:"},
                {"GetWorkerCommandResponse.getUserMessage.text", "Työläinen:"},
                {"InfoCommandResponse.getUserMessage.text", "Kokoelman tiedot:\n"},
                {"ListAPICommandResponse.getUserMessage.text", "Komentojen vastaus:"},
                {"OrganisationCommandResponse.getUserMessage.text", "Organisaatiot:\n"},
                {"UniqueOrganisationCommandResponse.getUserMessage.text", "Organisaatio-kentän yksilölliset arvot:\n"},
                {"PrintDescendingCommandResponse.getUserMessage.text", "Keräyselementit laskevassa järjestyksessä"},
                {"DBInsertApplication.addError.text", "Lisääminen ei onnistu"},
                {"DBInsertApplication.noElementError.text", "Ei elementtejä"},
                {"DBInsertApplication.notMaximum.text", "Ei maksimi"},
                {"DBInsertApplication.notMinimum.text", "Ei vähimmäismäärä"},
                {"DBReadExecutor.noWorkerById.text", "Ei työntekijää, jolla on annettu tunnus"},
                {"DBRemoveApplication.noEntity.text", "Poistettavaa entiteettiä ei löydy"},
                {"DBUpdateApplication.error.text", "Entiteettiä ei voitu päivittää"},
                {"OperationType.readError.text", "Lukeminen epäonnistui"},
                {"OperationType.writeError.text", "Kirjoitus epäonnistui"},
                {"OperationType.readPermissionError.text", "Ei lukuoikeutta nykyiselle käyttäjälle"},
                {"OperationType.writePermissionError.text", "Ei kirjoitusoikeutta nykyiselle käyttäjälle"},
                {"OperationType.createPermissionError.text", "Ei oikeuksia luoda tiedostoa"},
                {"OperationType.invalidPath.text", "ei ehkä ole tavallinen tiedosto tai sen polku on virheellinen"},
                {"OperationType.updatePermissionError.text", "Käyttöoikeuksien päivitys epäonnistui"},
                {"FileAccessException.fileAccessError.text", "Tiedoston käyttö epäonnistui"},
                {"FileAccessException.title.text", "Tiedostojen käyttö"},
                {"Executor.skip.text", "Tiedoston käyttö epäonnistui"},
                {"Executor.validationError.text", "Vahvistus epäonnistui"},
                {"ErrorHandlingApplication.methodNotAvailable", "Menetelmä ei ole käytettävissä"},
                {"OrganizationTableModel.Column.ID.text", "HENKILÖLLISYYSTODISTUS"},
                {"OrganizationTableModel.Column.Name.text", "Nimi"},
                {"OrganizationTable.deleteError.text", "Poisto epäonnistui"},
                {"ScriptLocalCommand.invalidScript.text", "Virheellinen komentosarja"},
                {"ScriptLocalCommand.noScriptFile.text", "Komentosarjatiedostoa ei läpäisty"},
                {"ScriptInvoker.default.success", "Menestys!"},
                {"ScriptInvoker.scriptFailed.text", "Komentosarja epäonnistui:"},
                {"ScriptInvoker.scriptFailed.unknownError.text", "Tuntematon virhe"},
                {"ScriptInvoker.help.text", "Jumala auttaa"}
        };
    }
}