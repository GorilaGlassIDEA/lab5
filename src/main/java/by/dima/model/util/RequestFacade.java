package by.dima.model.util;

import by.dima.model.client.parser.ForDeserializableAnswerDTO;
import by.dima.model.client.parser.ForSerializableObject;
import by.dima.model.client.request.Clientable;
import by.dima.model.common.AnswerDTO;
import by.dima.model.common.AuthRequestDTO;

/**
 * Данный класс инкапсулирует в себе отправку и прием запросов соблюдая API
 */
public class RequestFacade {

    private final ForSerializableObject<AuthRequestDTO> authSerialize;
    private final Clientable clientable;
    private final ForDeserializableAnswerDTO<AnswerDTO> forDeserializableAnswerDTO;

    public RequestFacade(ForDeserializableAnswerDTO<AnswerDTO> forDeserializableAnswerDTO, ForSerializableObject<AuthRequestDTO> authSerialize, Clientable clientable) {
        this.authSerialize = authSerialize;
        this.clientable = clientable;
        this.forDeserializableAnswerDTO = forDeserializableAnswerDTO;
        //TODO: напоминалка - при ответе AUTHORIZATION прикреплять username и password к запросу
    }

    public AnswerDTO getAnswer(AuthRequestDTO authRequestDTO) {
        clientable.makePost(authSerialize.serial(authRequestDTO));
        return forDeserializableAnswerDTO.deserial(clientable.makeGet());
    }

}
