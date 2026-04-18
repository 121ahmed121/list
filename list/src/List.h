#pragma once
#include "ListNode.h"
#include <vector>
#include <string>

// Класс списка
class List {
public:
    ListNode* head = nullptr;
    ListNode* tail = nullptr;

    ~List(); // освобождение памяти

    void loadFromFile(const std::string& filename);
    void serialize(const std::string& filename);
    void deserialize(const std::string& filename);

private:
    std::vector<ListNode*> nodes;
    void clear(); // очистка памяти
};
