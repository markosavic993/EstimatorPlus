package com.marko.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by msav on 7/31/2017.
 */
public enum CommunicationProtocol {
    REST,
    SOAP,
    UNDEFINED,
    NO_NEED_FOR_IMPLEMENTATION;
}
