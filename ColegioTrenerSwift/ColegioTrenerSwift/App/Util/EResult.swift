//
//  EResult.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI

enum EResult<T> {
    case success(_ data: T)
    case failure(_ err: String?)
}
