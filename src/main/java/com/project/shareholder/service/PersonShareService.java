package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.model.PersonShare;
import com.project.shareholder.request.PersonShareRequest;

public interface PersonShareService {
    // Add share
    PersonShare add(PersonShareRequest personShareRequest) throws DatabaseException;

    // Subtract share
    PersonShare subtract(PersonShareRequest personShareRequest) throws DatabaseException;
}
