#pragma once
#include <string>

// Узел двусвязного списка
struct ListNode {
    ListNode* prev = nullptr; // предыдущий элемент
    ListNode* next = nullptr; // следующий элемент
    ListNode* rand = nullptr; // случайный указатель
    std::string data;         // данные
};
