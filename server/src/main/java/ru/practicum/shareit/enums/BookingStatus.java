package ru.practicum.shareit.enums;

public enum BookingStatus {
    WAITING,    // Ожидает подтверждения
    APPROVED,   // Подтверждено владельцем
    REJECTED,   // Отклонено владельцем
    CANCELED    // Отменено создателем
}
