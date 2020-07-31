import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:ta_pronto/provider/users_provider.dart';
import 'package:ta_pronto/routes/app_routes.dart';
import 'package:ta_pronto/views/user_form.dart';
import 'package:ta_pronto/views/user_list.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(
          create: (ctx) => UsersProvider(),
        ),
      ],
      child: MaterialApp(
        title: 'TaPronto',
        debugShowCheckedModeBanner: false,
        theme: ThemeData(
          primarySwatch: Colors.indigo,
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        routes: {
          AppRoutes.HOME: (_) => UserList(),
          AppRoutes.USER_FORM: (_) => UserForm()
        },
      ),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('TaPronto')),
      body: Container(
        child: Center(
          child: Text('TaPronto'),
        ),
      ),
    );
  }
}
