#include "List.h"
#include <fstream>
#include <sstream>
#include <unordered_map>
#include <stdexcept>

// Деструктор
List::~List() {
    clear();
}

// Очистка памяти
void List::clear() {
    for (auto node : nodes) {
        delete node;
    }
    nodes.clear();
    head = tail = nullptr;
}

// Загрузка из текстового файла
void List::loadFromFile(const std::string& filename) {
    std::ifstream file(filename);
    if (!file) throw std::runtime_error("Ошибка открытия файла");

    std::string line;
    std::vector<int> randIdx;

    while (std::getline(file, line)) {
        std::stringstream ss(line);
        std::string data;
        int idx;

        if (!std::getline(ss, data, ';') || !(ss >> idx))
            throw std::runtime_error("Ошибка формата");

        auto node = new ListNode();
        node->data = data;

        if (!nodes.empty()) {
            node->prev = nodes.back();
            nodes.back()->next = node;
        }

        nodes.push_back(node);
        randIdx.push_back(idx);
    }

    head = nodes.empty() ? nullptr : nodes[0];
    tail = nodes.empty() ? nullptr : nodes.back();

    for (size_t i = 0; i < nodes.size(); i++) {
        if (randIdx[i] != -1) {
            if (randIdx[i] < 0 || randIdx[i] >= (int)nodes.size())
                throw std::runtime_error("Неверный rand индекс");

            nodes[i]->rand = nodes[randIdx[i]];
        }
    }
}

// Сериализация в бинарный файл
void List::serialize(const std::string& filename) {
    std::ofstream file(filename, std::ios::binary);
    if (!file) throw std::runtime_error("Ошибка записи");

    size_t n = nodes.size();
    file.write((char*)&n, sizeof(n));

    std::unordered_map<ListNode*, int> map;
    for (size_t i = 0; i < n; i++) map[nodes[i]] = i;

    for (size_t i = 0; i < n; i++) {
        size_t len = nodes[i]->data.size();
        file.write((char*)&len, sizeof(len));
        file.write(nodes[i]->data.c_str(), len);

        int r = -1;
        if (nodes[i]->rand) r = map[nodes[i]->rand];

        file.write((char*)&r, sizeof(r));
    }
}

// Десериализация из бинарного файла
void List::deserialize(const std::string& filename) {
    std::ifstream file(filename, std::ios::binary);
    if (!file) throw std::runtime_error("Ошибка чтения");

    clear();

    size_t n;
    file.read((char*)&n, sizeof(n));

    nodes.reserve(n);
    std::vector<int> randIdx(n);

    for (size_t i = 0; i < n; i++) {
        auto node = new ListNode();

        size_t len;
        file.read((char*)&len, sizeof(len));

        node->data.resize(len);
        file.read(&node->data[0], len);

        if (!nodes.empty()) {
            node->prev = nodes.back();
            nodes.back()->next = node;
        }

        nodes.push_back(node);
        file.read((char*)&randIdx[i], sizeof(int));
    }

    head = nodes.empty() ? nullptr : nodes[0];
    tail = nodes.empty() ? nullptr : nodes.back();

    for (size_t i = 0; i < n; i++) {
        if (randIdx[i] != -1) {
            if (randIdx[i] < 0 || randIdx[i] >= (int)nodes.size())
                throw std::runtime_error("Ошибка rand индекса");

            nodes[i]->rand = nodes[randIdx[i]];
        }
    }
}
