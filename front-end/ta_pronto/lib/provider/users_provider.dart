import 'dart:math';

import 'package:flutter/material.dart';
import 'package:ta_pronto/data/dummy_users.dart';
import 'package:ta_pronto/models/user.dart';

class UsersProvider with ChangeNotifier {
  final Map<String, User> _items = {...DUMMY_USERS};

  List<User> get all {
    return [..._items.values];
  }

  int get count {
    return _items.length;
  }

  User byIndex(int i) {
    return _items.values.elementAt(i);
  }

  void put(User user) {
    if (user == null) {
      return;
    }
    if (user.id != null &&
        user.id.trim().isEmpty &&
        _items.containsKey(user.id)) {
      _items.update(
          user.id,
          (_) => User(
              id: user.id,
              name: user.name,
              cpf: user.cpf,
              email: user.email,
              phone: user.phone,
              role: user.role));
    } else {
      final id = Random().nextDouble().toString();
      _items.putIfAbsent(
          id,
          () => User(
              id: id,
              name: user.name,
              cpf: user.cpf,
              email: user.email,
              phone: user.phone,
              role: user.role));
    }

    notifyListeners();
  }

  void remove(User user) {
    if (user != null && user.id != null) {
      _items.remove(user.id);
      notifyListeners();
    }
  }
}
